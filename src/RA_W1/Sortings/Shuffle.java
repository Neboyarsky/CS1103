package RA_W1.Sortings;

public class Shuffle {
    /**
     * Postcondition: The items in A have been rearranged into a random order.
     */
    static void shuffle(int[] A) {
        for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
// Choose a random location from among 0,1,...,lastPlace.
            int randLoc = (int)(Math.random()*(lastPlace+1));
// Swap items in locations randLoc and lastPlace.
            int temp = A[randLoc];
            A[randLoc] = A[lastPlace];
            A[lastPlace] = temp;
        }
    }

    public static void main(String[] args) {
        int arrayToShuffle[] = {3, 5, 8, 11, 4, 7, 18, 2, 1};

        shuffle(arrayToShuffle);

        for (int i = 0; i < arrayToShuffle.length; i++) {
            System.out.print(arrayToShuffle[i] + " ");
        }
    }
}