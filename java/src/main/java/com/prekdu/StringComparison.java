package com.prekdu;

import java.util.Scanner;

public class StringComparison {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Take input for the first string
    System.out.print("Enter the first string: ");
    String firstString = scanner.nextLine();

    // Take input for the second string
    System.out.print("Enter the second string: ");
    String secondString = scanner.nextLine();

    // Print the length of the first string
    System.out.println("Length of the first string: " + firstString.length());

    // Print the length of the second string
    System.out.println("Length of the second string: " + secondString.length());

    // Check if the lengths match
    if (firstString.length() == secondString.length()) {
      System.out.println("The lengths of the two strings match.");
    } else {
      System.out.println("The lengths of the two strings do not match.");
    }

    // Check if the two strings are the same
    if (firstString.equals(secondString)) {
      System.out.println("The two strings are the same.");
    } else {
      System.out.println("The two strings are not the same.");
    }

    scanner.close();
  }
}
