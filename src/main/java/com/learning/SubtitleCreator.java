package com.learning;

import java.io.File;
import java.io.IOException;

public class SubtitleCreator {
    public static void main(String[] args) {
        // Define the input file path
        String videoFilePath = "src/input/video.mp4";  // Correct path to your input video file

        // Check if the input file exists
        File inputFile = new File(videoFilePath);
        if (!inputFile.exists()) {
            System.err.println("Input video file not found: " + videoFilePath);
            return;
        }

        // Extract the base directory from the input video file path
        String baseDirectory = inputFile.getParent(); // This will be 'src/input'

        // Define the output file paths using the base directory
        String audioFilePath = baseDirectory + File.separator + "audio.wav";
        String subtitleFilePath = baseDirectory + File.separator + "subtitles.srt";

        // Delete existing files if they exist
        deleteIfExists(audioFilePath);
        deleteIfExists(subtitleFilePath);

        try {
            // Step 1: Extract audio from video
            VideoToAudioConverter.extractAudio(videoFilePath, audioFilePath);

            // Step 2: Transcribe audio to text using whisper.cpp
            String transcript = WhisperTranscription.transcribeAudio(audioFilePath);

            // Step 3: Generate subtitles in SRT format
            SubtitleGenerator.generateSRT(transcript, subtitleFilePath);

            System.out.println("Subtitles created successfully at " + subtitleFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Transcription process was interrupted.");
        } catch (RuntimeException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deleteIfExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.err.println("Failed to delete existing file: " + filePath);
            } else {
                System.out.println("Deleted existing file: " + filePath);
            }
        }
    }
}
