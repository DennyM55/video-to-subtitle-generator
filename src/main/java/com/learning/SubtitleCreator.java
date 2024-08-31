package com.learning;

import java.io.File;
import java.io.IOException;

public class SubtitleCreator {
    public static void main(String[] args) {
        // Define the input and output file paths
        String videoFilePath = "src/input/video.mp4"; // Update this path if the input file is somewhere else
        String audioFilePath = "output/audio.wav"; // Path where the extracted audio will be saved
        String subtitleFilePath = "output/subtitles.srt"; // Path where the subtitles will be saved

        // Ensure output directory exists
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try {
            // Step 1: Extract audio from video
            VideoToAudioConverter.extractAudio(videoFilePath, audioFilePath);

            // Step 2: Transcribe audio to text using OpenAI API
            String transcript = SpeechToTextConverter.transcribeAudio(audioFilePath);

            // Step 3: Generate subtitles in SRT format
            SubtitleGenerator.generateSRT(transcript, subtitleFilePath);

            System.out.println("Subtitles created successfully at " + subtitleFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
