/*
Name: Natasha Foreman
Course: CSD 420 – Advanced Java 
Date: April 12th, 2026
Assignment: Module 4
Purpose: Compare traversal time of a LinkedList.
*/

import java.util.Iterator;
import java.util.LinkedList;

public class ForemanAssignment_4 {

    public static void main(String[] args) {
        try {
            System.out.println("Testing 50,000 integers");
            runTest(50000);

            System.out.println("\nTesting 500,000 integers");
            runTest(500000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void runTest(int size) {
        LinkedList<Integer> list = createList(size);

        // Iterator timing
        long start1 = System.nanoTime();
        long sum1 = traverseIterator(list);
        long end1 = System.nanoTime();

        // get(index) timing
        long start2 = System.nanoTime();
        long sum2 = traverseGet(list);
        long end2 = System.nanoTime();

        System.out.println("Size: " + size);
        System.out.println("Iterator time: " + (end1 - start1));
        System.out.println("get(index) time: " + (end2 - start2));

        // Test to ensure correctness
        if (sum1 == sum2) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
    }

    public static LinkedList<Integer> createList(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        return list;
    }

    public static long traverseIterator(LinkedList<Integer> list) {
        long sum = 0;
        Iterator<Integer> it = list.iterator();

        while (it.hasNext()) {
            sum += it.next();
        }

        return sum;
    }

    public static long traverseGet(LinkedList<Integer> list) {
        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }
}
/*
Results:
The iterator method was faster than the get(index) method for both tests.

When testing with 50,000 integers, the difference was noticeable,
but when testing with 500,000 integers, the get(index) method took
significantly longer.

This happens because LinkedList does not support fast random access.
The iterator moves through the list one element at a time efficiently,
while get(index) must traverse from the beginning each time.

As the list size increases, the performance difference becomes much larger.
This shows that using an iterator is more efficient for LinkedList traversal.
*/