package com.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoToAudioConverter {
    public static void extractAudio(String inputVideoPath, String outputAudioPath) throws IOException {
        System.out.println("Starting audio extraction...");

        // Convert audio to 16kHz mono WAV
        String ffmpegCommand = String.format("ffmpeg -i \"%s\" -ar 16000 -ac 1 -c:a pcm_s16le \"%s\"", inputVideoPath, outputAudioPath);
        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand.split(" "));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        System.out.println("Running FFmpeg command to extract audio...");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Log FFmpeg output to console
            }
        }

        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Audio extracted and converted to 16kHz mono WAV successfully.");
            } else {
                System.err.println("Failed to extract audio. Exit code: " + exitCode);
                throw new IOException("FFmpeg failed with exit code " + exitCode);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Audio extraction process was interrupted.");
        }
    }
}
