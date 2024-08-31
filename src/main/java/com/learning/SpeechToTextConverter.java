package com.learning;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SpeechToTextConverter {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/audio/transcriptions";
    private static final String API_KEY = System.getenv("OPENAI_API_KEY"); // Ensure this environment variable is set

    public static String transcribeAudio(String audioFilePath) throws IOException {
        // Encode the audio file in base64
        byte[] audioBytes = Files.readAllBytes(Path.of(audioFilePath));
        String encodedAudio = Base64.getEncoder().encodeToString(audioBytes);

        // Prepare JSON payload
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("audio", encodedAudio);
        jsonMap.put("model", "whisper-1"); // Specify the model to use

        String json = new ObjectMapper().writeValueAsString(jsonMap);

        // Set up HTTP client and request
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_ENDPOINT);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);
        httpPost.setEntity(new StringEntity(json));

        // Send request and get response
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getCode() != 200) {
                throw new RuntimeException("Request failed with status: " + response.getCode());
            }
            return new ObjectMapper().readTree(response.getEntity().getContent()).get("text").asText();
        }
    }
}
