package com.chemi.lab.openai.service;

import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.openai.models.response.*;
import com.chemi.lab.openai.repo.GPTResponseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class ChatClientService {
    private final ChatClient chatClient;
    private final GPTResponseRepo gptResponseRepo;

    public ChatClientService(ChatClient.Builder build, GPTResponseRepo gptResponseRepo){
        this.chatClient = build.build();
        this.gptResponseRepo = gptResponseRepo;
    }

    public GPTResponseDTO callOpenai(){
        String PROMPT = "Based on the soil properties: Nitrogen 100 mg/kg,pH= 13.54, Phosphorus 100 mg/kg, Potassium 800 mg/kg, Conductivity 100 µS/cm, Moisture 30.00%, " +
                "Temperature 14.40°C, and pH 5.75, recommend the best type of fertilizer to optimize crop growth, considering nutrient balance and soil conditions." +
                " For a crop like maize, please make use more words to explain the information sufficiently, use at least 15 words for each sentence" +
                "make the summary key 300 characters long";
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

            GPTResponse gptResponse = gptResponseRepo.save(response);

            List<PropertiesConfig> propertiesConfigs = Stream.of(
                    soilProperties.getNitrogen(),
                    soilProperties.getPhosphorus(),
                    soilProperties.getPotassium(),
                    soilProperties.getConductivity(),
                    soilProperties.getMoisture(),
                    soilProperties.getTemperature(),
                    soilProperties.getPH()
            ).map(PropertiesConfig::new).toList();
            return new GPTResponseDTO(response, propertiesConfigs);
        } catch (Exception e) {
            throw new ApiResourceNotFoundException(e.getMessage());
        }
    }
}
