//Insertion sorting took 5 milliseconds to sort 1 000 items
//API sorting took 1 milliseconds to sort 1 000 items

//Insertion sorting took 12159591 milliseconds to sort 100 000 items
//API sorting took 44 milliseconds to sort 100 000 items

//API sorting took 16 milliseconds to sort 1 000 000 items

package Week1.Labs.Lab2;

import java.time.Instant;
import java.util.Arrays;

public class Lab2Part1 {
    static void insertionSort(int[] A) {
// Sort the array A into increasing order.
        int itemsSorted;    // Number of items that have been sorted by current iteration of for-loop
        // begins with 1 - first element of array is always sorted
        for (itemsSorted = 1; itemsSorted < A.length; itemsSorted++) {
// Assume that items A[0], A[1], ... A[itemsSorted-1]
// have already been sorted. Insert A[itemsSorted]
// into the sorted part of the list.
            int temp = A[itemsSorted]; // The item to be inserted.
            int loc = itemsSorted - 1; // Start at end of list.
            while (loc >= 0 && A[loc] > temp) {
                A[loc + 1] = A[loc]; // Bump item from A[loc] up to loc+1.
                loc = loc - 1; // Go on to next location.
            }
            A[loc + 1] = temp; // Put temp in last vacated space.
        }
    }

    public static void main(String[] args) {
        int ARR_SIZE = 100000; // defines arrays size
        int MAX_VALUE = 10000; // defines max value of array element
        int[] arr1 = new int[ARR_SIZE];
        int[] arr2 = new int[ARR_SIZE];


//        System.out.println("Unsorted arrays:");
//        System.out.println("Array 1: Array2:");
        for (int i = 0; i < ARR_SIZE; i++) {
            arr1[i] =  (int)(Math.random() * MAX_VALUE);
            arr2[i] = arr1[i];
//            System.out.printf("%7d", arr1[i]);
//            System.out.printf("%7d", arr2[i]);
//            System.out.println();
        }

//          System.out.println("Sorted arrays:");
//          System.out.println("Array 1: Array2:");

          long startTimeInsertion = System.currentTimeMillis();
          insertionSort(arr1);
          long runTimeInsertion = System.currentTimeMillis() - startTimeInsertion;

        Instant now = Instant.now();
        long startTimeAPI = now.toEpochMilli();

//        long startTimeAPI = System.currentTimeMillis();
        Arrays.sort(arr2);
        long runTimeAPI = System.currentTimeMillis() - startTimeAPI;

//        for (int i = 0; i < ARR_SIZE; i++) {
//            System.out.printf("%7d", arr1[i]);
//            System.out.printf("%7d", arr2[i]);
//            System.out.println();
//        }

        System.out.println("Insertion sorting took " + runTimeInsertion + " milliseconds to sort " + ARR_SIZE + " items");
        System.out.println("API sorting took " + runTimeAPI + " milliseconds to sort " + ARR_SIZE + " items");

        System.out.print(startTimeAPI);

    }
}
