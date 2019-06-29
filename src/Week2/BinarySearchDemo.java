package Week2;

import java.util.Arrays;

public class BinarySearchDemo {

    public static void main(String[] args) {
        int SIZE = 30;
        int MAX_VALUE = 100;
        int[] testArray = MyArrayOperations.generateArray(SIZE, MAX_VALUE);
        MyArrayOperations.printArray(testArray);
        Arrays.sort(testArray);
        int valueToSearch = (int)(MAX_VALUE * Math.random());
        System.out.printf("Looking for the position of a random number, say, %d in array.%n", valueToSearch);
        System.out.println("Sorting the array... Sorted!");
        MyArrayOperations.printArray(testArray);
        System.out.print("Starting the search, the index of " + valueToSearch + " is ");
        System.out.println(MyArrayOperations.binarySearch(testArray, 0, testArray.length, valueToSearch)+1);
        if (MyArrayOperations.binarySearch(testArray, 0, testArray.length, valueToSearch) == -1) {
            System.out.println("This means, " + valueToSearch + " did not appear in the array.");
        }
    }
}
