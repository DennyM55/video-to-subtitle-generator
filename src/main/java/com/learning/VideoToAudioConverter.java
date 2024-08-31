package com.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoToAudioConverter {
    public static void extractAudio(String inputVideoPath, String outputAudioPath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg", "-i", inputVideoPath, "-q:a", "0", "-map", "a", outputAudioPath
        );
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Log FFmpeg output to console
            }
        }

        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Audio extracted successfully.");
            } else {
                System.err.println("Failed to extract audio. Exit code: " + exitCode);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
