/*
Name: Natasha Foreman
Course: CSD 420 – Advanced Java 
Date: April 5th, 2026
Assignment: Module 3
Purpose: Write a test program that contains a static method that returns a new ArrayList.
*/

import java.util.Random;
public class TestRemoveDuplicates {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();

        // 50 random values from 1 to 20
        for (int i = 0; i < 50; i++) {
            list.add(rand.nextInt(20) + 1);
        }

        System.out.println("Original List:");
        System.out.println(list);

        ArrayList<Integer> newList = removeDuplicates(list);

        System.out.println("List without duplicates:");
        System.out.println(newList);
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        ArrayList<E> newList = new ArrayList<>();

        for (E item : list) {
            if (!newList.contains(item)) {
                newList.add(item);
            }
        }

        return newList;
    }
}