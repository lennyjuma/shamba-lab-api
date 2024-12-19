package com.chemi.lab.openai.service;

import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.mkulima.crop.Crop;
import com.chemi.lab.openai.models.response.*;
import com.chemi.lab.openai.repo.GPTResponseRepo;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.soil.SoilRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static jdk.internal.org.jline.utils.Colors.s;

@Slf4j
@Service
public class ChatClientService {
    private final ChatClient chatClient;
    private final GPTResponseRepo gptResponseRepo;
    private final SoilRepo soilRepo;

    public ChatClientService(ChatClient.Builder build, GPTResponseRepo gptResponseRepo, SoilRepo soilRepo){
        this.chatClient = build.build();
        this.gptResponseRepo = gptResponseRepo;
        this.soilRepo = soilRepo;
    }

    public GPTResponseDTO callOpenai(Soil soil){
        String PROMPT = getPrompt(soil);
        GPTResponse response = null;
        try {
            response = chatClient.prompt(PROMPT)
                    .call()
                    .entity(GPTResponse.class);
            SoilProperties soilProperties = response.getSoilProperties();
            soilProperties.getNitrogen().setName("nitrogen");
            soilProperties.getPhosphorus().setName("phosphorus");
            soilProperties.getPotassium().setName("potassium");
            soilProperties.getConductivity().setName("conductivity");
            soilProperties.getMoisture().setName("moisture");
            soilProperties.getTemperature().setName("temperature");
            soilProperties.getPH().setName("pH");
            log.info("ai response {} ",response);
            response.setSoilReadingId(soil.getId());

            GPTResponse gptResponse = gptResponseRepo.save(response);

            return getGPTResponseDTO(gptResponse);
        } catch (Exception e) {
            throw new ApiResourceNotFoundException(e.getMessage());
        }
    }

    private static String getPrompt(Soil soil) {
        List<String> list = soil.getShamba().getCrops().stream().map(Crop::getName).toList();
        //convert list to string
        String crops = String.join(", ", list);
        String prompt = "Based on the soil properties: Nitrogen " + soil.getNitrogen() + " mg/kg,pH= " + soil.getPH() + ", Phosphorus " + soil.getPhosphorous() + " mg/kg, Potassium " + soil.getPotassium() + " mg/kg, " +
                "Conductivity " + soil.getConductivity() + " µS/cm, Moisture " + soil.getMoisture() + " %, " +
                "Temperature 14.40°C, and pH 5.75, recommend the best type of fertilizer to optimize crop growth, considering nutrient balance and soil conditions." +
                " For a crop like " + crops + ", please make use more words to explain the information sufficiently, use at least 15 words for each sentence" +
                "make the summary key 300 characters long";
        log.info("prompt {}", prompt);
        return prompt;
    }
    private static GPTResponseDTO getGPTResponseDTO(GPTResponse gptResponse) {
        SoilProperties soilProperties = gptResponse.getSoilProperties();
        List<PropertiesConfig> propertiesConfigs = Stream.of(
                soilProperties.getNitrogen(),
                soilProperties.getPhosphorus(),
                soilProperties.getPotassium(),
                soilProperties.getConductivity(),
                soilProperties.getMoisture(),
                soilProperties.getTemperature(),
                soilProperties.getPH()
        ).map(PropertiesConfig::new).toList();
        return new GPTResponseDTO(gptResponse, propertiesConfigs);

    }

    public GPTResponseDTO get_ai_results_by_soil_reading_id(String soilReadingId) {
        GPTResponse gptResponse = gptResponseRepo.findBySoilReadingId(soilReadingId).orElseThrow(() -> new ApiResourceNotFoundException("Generate ai recommendation first"));
        return getGPTResponseDTO(gptResponse);
    }

    public GPTResponseDTO generateAiRecommendation(String soilReadingId) {
        Soil soil = soilRepo.findById(soilReadingId)
                .orElseThrow(() -> new ApiResourceNotFoundException("Soil reading not found"));
        return callOpenai(soil);
    }
}
