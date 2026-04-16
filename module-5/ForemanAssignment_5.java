/*
Name: Natasha Foreman
Course: CSD 420 – Advanced Java 
Date: April 16th, 2026
Assignment: Module 5
Purpose: Read words from a file, remove duplicates, and display them in ascending and descending order.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class ForemanAssignment_5 {

    public static void main(String[] args) {
        try {
            // Create TreeSet
            TreeSet<String> words = new TreeSet<>();

            // Read file
            File file = new File("collection_of_words.txt");
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String word = input.next();
                words.add(word.toLowerCase());
            }

            input.close();

            // Display ascending order
            System.out.println("Ascending Order:");
            for (String word : words) {
                System.out.println(word);
            }

            // Display descending order
            System.out.println("\nDescending Order:");
            for (String word : words.descendingSet()) {
                System.out.println(word);
            }

            // Test check
            if (!words.isEmpty()) {
                System.out.println("\nTest Passed: Words loaded successfully.");
            } else {
                System.out.println("\nTest Failed: No words found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}