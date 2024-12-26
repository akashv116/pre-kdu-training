package com.prekdu;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CSVWordFrequency {
  public static void main(String[] args) {
    // Define the file path of the CSV file
    String filePath = "/Users/aakas/pre-kdu-training/java/src/main/resources/input.csv";

    // Create a map to store the word counts
    Map<String, Integer> wordCountMap = new HashMap<>();

    try {
      // Read all lines from the specified file
      List<String> lines = Files.readAllLines(Paths.get(filePath));

      // Iterate over each line in the file
      for (String line : lines) {
        // Split the line into words using a regular expression to handle non-word characters
        String[] words = line.split("\\W+");

        // Iterate over each word in the current line
        for (String word : words) {
          // Check if the word is not empty
          if (!word.isEmpty()) {
            // Convert the word to lowercase for case-insensitive counting
            word = word.toLowerCase();

            // Update the word count in the map
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
          }
        }
      }

      // Sort the word count map by values (frequencies) in descending order,
      // limit to the top 3 words, and print the result
      wordCountMap.entrySet().stream()
          .sorted(
              (a, b) ->
                  b.getValue().compareTo(a.getValue())) // Sort by frequency in descending order
          .limit(3) // Limit to the top 3 most frequent words
          .forEach(
              entry ->
                  System.out.println(
                      entry.getKey() + ": " + entry.getValue())); // Print the word and its count
    } catch (IOException e) {
      // Handle the exception if the file cannot be read
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
}
