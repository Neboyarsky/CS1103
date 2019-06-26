package Week1.RA_W1.Sortings;

public class InsertionSorting {
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
        int arrayToSort [] = {3, 5, 8, 11, 4, 7, 18, 2, 1};

        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.print(arrayToSort[i] + " ");
        }
        System.out.println(" ");

        insertionSort(arrayToSort);

        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.print(arrayToSort[i] + " ");
        }


    }
}
