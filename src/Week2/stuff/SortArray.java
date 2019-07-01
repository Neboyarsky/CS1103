package Week2.stuff;

import java.util.Arrays;

public class SortArray {

    public static void main(String[] args) {
        //NOTE: THE FOLLOWING UNITS FOR TIME ARE IN MILLISECONDS
        // For 1,000, the run time for selection sort was 5, and for Array.sort was 1
        // for 10,000, selection sort was 44 and Array sort was 0
        // for 100,000, selection sort was 5315 and Array sort was 5

        int a = 100000;

        int[] one = new int[a];

        for (int i = 0; i < one.length; i++) {
            int rand = (int) (Math.random() * 10);
            one[i] = rand;
            //System.out.println("rand is " + rand);
        }

        int[] two = one;

        long startTime = System.currentTimeMillis();
        selectionSort(one);
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("The runtime for the selection sort is " + runTime);

        long startTime2 = System.currentTimeMillis();
        Arrays.sort(two);
        long runTime2 = System.currentTimeMillis() - startTime2;
        System.out.println("The runtime for the Arrays.sort is " + runTime2);


    }

    static void selectionSort(int[] one) {
        for (int lastPlace = one.length-1; lastPlace > 0; lastPlace--) {

            int maxLoc = 0; // Location of largest item seen so far.
            for (int j = 1; j <= lastPlace; j++) {
                if (one[j] > one[maxLoc]) {
                    maxLoc = j;
                }
            }
            int temp = one[maxLoc]; // Swap largest item with A[lastPlace].
            one[maxLoc] = one[lastPlace];
            one[lastPlace] = temp;

        } // end of for loop
    }

}
