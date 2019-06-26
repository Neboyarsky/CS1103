package Week1.RA_W1.Searches;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class binarySearchDemo {
    /**
     * Searches the array A for the integer N.
     * Precondition: A must be sorted into increasing order.
     * Postcondition: If N is in the array, then the return value, i,
     * satisfies A[i] == N. If N is not in the array, then the
     * return value is -1.
     */
    static int binarySearch(int[] A, int N) {
        int lowestPossibleLoc = 0;
        int highestPossibleLoc = A.length - 1;
        while (highestPossibleLoc >= lowestPossibleLoc) {
            int middle = (lowestPossibleLoc + highestPossibleLoc) / 2;
            if (A[middle] == N) {
// N has been found at this index!
                return middle;
            }
            else if (A[middle] > N) {
// eliminate locations >= middle
                highestPossibleLoc = middle - 1;
            }
            else {
// eliminate locations <= middle
                lowestPossibleLoc = middle + 1;
            }
        }
// At this point, highestPossibleLoc < LowestPossibleLoc,
// which means that N is known to be not in the array. Return
// a -1 to indicate that N could not be found in the array.
        return -1;
    }

    public static void main(String[] args) {

        int[] sortedArray = new int[1000];
        sortedArray[0] = (int)(Math.random()*2);
        for (int i = 1; i < sortedArray.length; i++) {
            sortedArray[i] = sortedArray[i - 1] + (int)(Math.random()*3 + 1);
            System.out.println ("#" + (i + 1) + " element is " + sortedArray[i]);
        }

        int lookingFor = 1990;
        int x = binarySearch(sortedArray, lookingFor);
        System.out.println("? " + lookingFor + " is the " + (x + 1) + " element of the array");

    }
}
