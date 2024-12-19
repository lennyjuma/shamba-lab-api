package com.chemi.lab.openai.controller;

import com.chemi.lab.openai.models.response.GPTResponseDTO;
import com.chemi.lab.openai.service.ChatClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ai")
@RequiredArgsConstructor
public class ChatClientController {
    private final ChatClientService chatClient;

    @GetMapping
    public GPTResponseDTO sendMessage(@RequestParam(name = "soilReadingId") String soilReadingId) {
        return chatClient.get_ai_results_by_soil_reading_id(soilReadingId);
    }
    @GetMapping("generate")
    public GPTResponseDTO generateAiRecommendation(@RequestParam(name = "soilReadingId") String soilReadingId) {
        return chatClient.generateAiRecommendation(soilReadingId);
    }
}
