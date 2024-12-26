package com.prekdu;

import java.util.*;

public final class CollectionsExample {
  public static void main(String[] args) {
    // Create a scanner to read user input
    Scanner scanner = new Scanner(System.in);

    // Create an ArrayList to store strings
    List<String> arrayList = new ArrayList<>();

    // Create a HashSet to store unique strings
    Set<String> hashSet = new HashSet<>();

    // Create a HashMap to store strings and their frequencies
    Map<String, Integer> hashMap = new HashMap<>();

    // Prompt the user to enter 10 strings
    System.out.println("Enter 10 strings:");
    for (int i = 0; i < 10; i++) {
      String input = scanner.nextLine(); // Read user input

      // Add the string to the ArrayList
      arrayList.add(input);

      // Add the string to the HashSet (only unique values are stored in a HashSet)
      hashSet.add(input);

      // Update the frequency of the string in the HashMap
      hashMap.put(input, hashMap.getOrDefault(input, 0) + 1);
    }

    // Print the contents of the ArrayList
    System.out.println("\nContents of ArrayList:");
    for (String word : arrayList) {
      System.out.println(word);
    }

    // Print the contents of the HashSet
    System.out.println("\nContents of HashSet:");
    for (String word : hashSet) {
      System.out.println(word);
    }

    // Print the contents of the HashMap (word and its frequency)
    System.out.println("\nContents of HashMap:");
    for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    // Close the scanner to release resources
    scanner.close();
  }
}
