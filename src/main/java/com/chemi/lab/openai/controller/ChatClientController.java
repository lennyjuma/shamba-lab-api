package com.chemi.lab.openai.controller;

import com.chemi.lab.openai.models.response.GPTResponse;
import com.chemi.lab.openai.models.response.GPTResponseDTO;
import com.chemi.lab.openai.service.ChatClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai")
@RequiredArgsConstructor
public class ChatClientController {
    private final ChatClientService chatClient;

    @GetMapping
    public GPTResponseDTO sendMessage() {
        return chatClient.callOpenai();
    }
}
