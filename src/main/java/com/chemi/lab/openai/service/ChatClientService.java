package com.chemi.lab.openai.service;

import com.chemi.lab.openai.models.response.GPTResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatClientService {
    private final ChatClient chatClient;

    public ChatClientService(ChatClient.Builder build) {
        this.chatClient = build.build();
    }

    public GPTResponse callOpenai(){
        String PROMPT = "Based on the soil properties: Nitrogen 100 mg/kg,pH= 13.54, Phosphorus 100 mg/kg, Potassium 800 mg/kg, Conductivity 100 µS/cm, Moisture 30.00%, " +
                "Temperature 14.40°C, and pH 5.75, recommend the best type of fertilizer to optimize crop growth, considering nutrient balance and soil conditions." +
                " For a crop like maize, please make use more words to explain the information sufficiently, use at least 15 words for each sentence" +
                "make the summary key 300 characters long";
        GPTResponse response = chatClient.prompt(PROMPT)
                .call()
                .entity(GPTResponse.class);
        log.info("ai response {} ",response);
        return response;
    }
}
