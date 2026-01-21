package com.gom.travel.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    @Value("${gemini.api.key}")
    private String apiKey;

    Client client = new Client();

    GenerateContentConfig config = GenerateContentConfig
            .builder()
            .build();

    public String getAIRecommendation(String prompt) {
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null);

        return response.text();
    }
}
