package RA_W1.Searches;

public class Searches {
    /**
     * Searches the array A for the integer N. If N is not in the array,
     * then -1 is returned. If N is in the array, then return value is
     * the first integer i that satisfies A[i] == N.
     */
    static int find(int[] A, int N) {
        for (int index = 0; index < A.length; index++) {
            if ( A[index] == N )
                return index; // N has been found at this index!
        }
// If we get this far, then N has not been found
// anywhere in the array. Return a value of -1.
        return -1;
    }
}
