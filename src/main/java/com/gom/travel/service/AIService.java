package com.gom.travel.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    @Value("${gemini.api.key}")
    private String apiKey;

    Client client = new Client();

    GenerateContentConfig config = GenerateContentConfig
            .builder()
            .systemInstruction(Content.fromParts(Part.fromText("Respond ONLY with pure JSON, no markdown or explanations.")))
            .build();

    public String getAIRecommendation(String prompt) {
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null);

        return response.text();
    }
}
