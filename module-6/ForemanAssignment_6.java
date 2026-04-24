/*
Name: Natasha Foreman
Course: CSD 420 – Advanced Java 
Date: April 24th, 2026
Assignment: Module 6
Purpose: Write two generic bubble sort methods. One method sorts using the Comparable interface, and the second method sorts using the Comparator interface.
*/

import java.util.Comparator;

public class ForemanAssignment_6 {

    public static void main(String[] args) {

        try {
            // Bubble sort with Integers
            Integer[] numbers = {5, 2, 9, 1, 6};

            System.out.println("Original Integer Array:");
            printArray(numbers);

            bubbleSort(numbers);

            System.out.println("Sorted Integer Array using Comparable:");
            printArray(numbers);

            // Bubble sort with Strings
            String[] names = {"Mike", "Anna", "Chris", "Bob"};

            System.out.println("\nOriginal String Array:");
            printArray(names);

            bubbleSort(names, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });

            System.out.println("Sorted String Array using Comparator:");
            printArray(names);

            // Test code to ensure sorting worked
            if (isSorted(numbers) && isSorted(names)) {
                System.out.println("\nTest Passed: Arrays sorted correctly.");
            } else {
                System.out.println("\nTest Failed: Arrays not sorted correctly.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
    Generic bubble sort method using Comparable. This method sorts objects that already know how to compare themselves.
    */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {

        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;

            for (int i = 0; i < list.length - k; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    needNextPass = true;
                }
            }
        }
    }

    /*
    Generic bubble sort method using Comparator. This method sorts objects based on the rule provided by the Comparator.
    */
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {

        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;

            for (int i = 0; i < list.length - k; i++) {
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    needNextPass = true;
                }
            }
        }
    }

    /*
    Prints the array values.
    */
    public static <E> void printArray(E[] list) {
        for (E item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /*
    Test method to check if Comparable array is sorted.
    */
    public static <E extends Comparable<E>> boolean isSorted(E[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i].compareTo(list[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}