package Week2.stuff;

/***************Time Reports***************/

/**

 * For array limit 1000:
 * Run time of the Selection sort is:0.004 Seconds
 * Run time of the In Built Sort method: 0.001 Seconds

 * For array limit 10000:
 * Run time of the Selection sort is:0.05 Seconds
 * Run time of the In Built Sort method: 0.002 Seconds

 * For array limit 100000:
 * Run time of the Selection sort is:9.629 Seconds
 * Run time of the In Built Sort method: 0.008 Seconds

 **/


import java.util.Arrays;

import java.util.Scanner;

public class BSA1 {

    public static void selectionSort(int arr[])

    {

        int n = arr.length; // move the boundary of the unsorted array one by one

        for (int i = 0; i < n-1; i++)

        {

            // This will get the minimum in the array

            int min_idx = i;

            for (int j = i+1; j < n; j++)

                if (arr[j] < arr[min_idx])

                    min_idx = j;

            // Now we swap the minimum element to become the first element


            int temp = arr[min_idx];

            arr[min_idx] = arr[i];

            arr[i] = temp;

        }

    }

    public static void printArrayBySelectionSort(int[] arr) {

        for(int i=0;i<arr.length;i++) {

            System.out.print(arr[i]+" ");

        }

    }

    public static void sortByInBuilt(int arr[]) {

        Arrays.sort(arr);

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the limit of the array:");

        int limit = scan.nextInt();

        int[] arr = new int[limit];

        for(int i = 0;i<arr.length;i++) {

            arr[i] = (int) (Math.random() * 100);

        }

        long startTime = System.currentTimeMillis();

        selectionSort(arr);

        long runTime = System.currentTimeMillis() - startTime;

        System.out.println("\nRun time of the Selection sort is:"+runTime/1000.0+" Seconds");

        long startTime1 = System.currentTimeMillis();

        sortByInBuilt(arr);

        long runTime1 = System.currentTimeMillis()-startTime1;

        System.out.println("\nRun time of the In Built Sort method: "+runTime1/1000.0+" Seconds");

        printArrayBySelectionSort(arr);

    }

}