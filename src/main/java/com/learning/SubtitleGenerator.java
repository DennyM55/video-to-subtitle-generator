package com.learning;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SubtitleGenerator {
    public static void generateSRT(String transcript, String subtitleFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(subtitleFilePath))) {
            String[] lines = transcript.split("\n");
            int counter = 1;
            int startTime = 0;

            for (String line : lines) {
                writer.write(counter + "\n");
                writer.write(formatTime(startTime) + " --> " + formatTime(startTime + 2000) + "\n"); // Example timing
                writer.write(line + "\n\n");
                startTime += 2000; // Increment start time for each subtitle
                counter++;
            }
        }

        System.out.println("SRT file generated successfully.");
    }

    private static String formatTime(int millis) {
        int hours = millis / (1000 * 60 * 60);
        int minutes = (millis % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (millis % (1000 * 60)) / 1000;
        int milliseconds = millis % 1000;
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, milliseconds);
    }
}
