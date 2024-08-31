package com.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WhisperTranscription {
    public static String transcribeAudio(String audioFilePath) throws IOException, InterruptedException {
        System.out.println("Starting offline transcription using whisper.cpp...");

        String modelPath = "C:\\Users\\Reflection-X\\Dev\\Github\\java-x\\whisper.cpp\\bin\\ggml-model-whisper-tiny-q5_1.bin";
        String whisperExePath = "C:\\Users\\Reflection-X\\Dev\\Github\\java-x\\whisper.cpp\\bin\\whisper.exe";

        ProcessBuilder whisperProcessBuilder = new ProcessBuilder(
                whisperExePath,
                "-m", modelPath,
                "-f", audioFilePath,
                "-osrt"  // Output format SRT
        );

        whisperProcessBuilder.redirectErrorStream(true);
        Process whisperProcess = whisperProcessBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(whisperProcess.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            output.append(line).append("\n");
        }

        // Wait for the process to complete
        int exitCode = whisperProcess.waitFor();
        if (exitCode == 0) {
            System.out.println("Transcription completed successfully.");
        } else {
            throw new RuntimeException("Failed to transcribe audio. Exit code: " + exitCode);
        }

        return output.toString();
    }
}
