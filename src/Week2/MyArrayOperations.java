package Week2;

import java.util.Arrays;

public class MyArrayOperations {

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
    public static void main(String[] args) {
        int[] x = { 8, 0, 4, 7, 3, 7, 10, 12, -3 };
        System.out.println("Было");
        System.out.println(Arrays.toString(x));

        int low = 0;
        int high = x.length - 1;

        quickSort(x, low, high);
        System.out.println("Стало");
        System.out.println(Arrays.toString(x));
    }
    /**
     * Search in the array A in positions numbered loIndex to hiIndex,
     * inclusive, for the specified value. If the value is found, return
     * the index in the array where it occurs. If the value is not found,
     * return -1. Precondition: The array must be sorted into increasing
     * order.
     */
    static int binarySearch(int[] A, int loIndex, int hiIndex, int value) {
        if (loIndex > hiIndex) {
// The starting position comes after the final index,
// so there are actually no elements in the specified
// range. The value does not occur in this empty list!
            return -1;
        }
        else {
// Look at the middle position in the list. If the
// value occurs at that position, return that position.
// Otherwise, search recursively in either the first
// half or the second half of the list.
            int middle = (loIndex + hiIndex) / 2;
            if (value == A[middle])
                return middle;
            else if (value < A[middle])
                return binarySearch(A, loIndex, middle - 1, value);
            else // value must be > A[middle]
                return binarySearch(A, middle + 1, hiIndex, value);
        }
    } // end binarySearch()

    static int[] generateArray(int size, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int)(maxValue * Math.random());
        }
        return array;
    }

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element #" + (i + 1) + " is " + array[i]);
        }
    }
}