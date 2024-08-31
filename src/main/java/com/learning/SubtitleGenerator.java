package com.learning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class SubtitleGenerator {
    public static void generateSRT(String transcript, String outputSRTPath) throws IOException {
        String[] lines = transcript.split("\\. ");
        StringBuilder srtContent = new StringBuilder();

        int counter = 1;
        for (String line : lines) {
            srtContent.append(counter++).append("\n");
            srtContent.append("00:00:").append(String.format("%02d", (counter * 2) % 60)).append(",000 --> 00:00:")
                    .append(String.format("%02d", (counter * 2 + 2) % 60)).append(",000").append("\n");
            srtContent.append(line).append("\n\n");
        }

        Files.write(Paths.get(outputSRTPath), srtContent.toString().getBytes(StandardCharsets.UTF_8));
    }
}
