package Week1.RA_W1.Sortings;

public class SelectionSort {
    static void selectionSort(int[] A) {
// Sort A into increasing order, using selection sort
        for (int lastPlace = A.length - 1; lastPlace > 0; lastPlace--) {
// Find the largest item among A[0], A[1], ...,
// A[lastPlace], and move it into position lastPlace
// by swapping it with the number that is currently
// in position lastPlace.
            int maxLoc = 0; // Position of largest item seen so far.
            for (int j = 1; j <= lastPlace; j++) {
                if (A[j] > A[maxLoc]) {
// Since A[j] is bigger than the maximum we’ve seen
// so far, j is the new location of the maximum value
// we’ve seen so far.
                    maxLoc = j;
                }
            }
            int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
            A[maxLoc] = A[lastPlace];
            A[lastPlace] = temp;
        } // end of for loop
    }

    public static void main(String[] args) {
        int arrayToSort[] = {3, 5, 8, 11, 4, 7, 18, 2, 1};

        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.print(arrayToSort[i] + " ");
        }
        System.out.println(" ");

        selectionSort(arrayToSort);

        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.print(arrayToSort[i] + " ");
        }

    }
}
