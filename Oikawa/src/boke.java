import java.util.Arrays;

class Kags {

    public void BubbleSort (int array[]) {
        int size = array.length;

        // process
        for (int i = 0; i < size -  1; i++) {
            for (int j = 0; j < size - 1 - 1; j++) {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j +1] = temp;
            }
        }
    }

    public void InsertSort (int array []) {
        int size = array.length;

        // process
        for (int i = 1; i < size; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;

        }
    }

    public void SelectionSort (int array []) {
        int size = array.length;

        // process
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;

        }
    }

    public int LinearSearch (int[] array, int key) {
        int size = array.length;

        // process

        for (int i = 0; i < size; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public int BinarySeacrch (int[] array, int key) {
        int size = array.length;
        int left = 0;
        int right = size - 1;
        // process

        while (left<=right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return - 1;
    }
}


// main
public class boke {
    public static void main(String[] args) {

        int array [] = {5, 7, 9, 1, 3, 2, 6, 4, 8};
        int element = 3;
        // calling unsorted array
        System.out.println("Original Array: " + Arrays.toString(array));

        // creating object
        Kags kags = new Kags();
        // calling method
        int result1 = kags.LinearSearch(array, 3);
        kags.BubbleSort(array);
        kags.InsertSort(array);
        kags.SelectionSort(array);
        int result2 = kags.BinarySeacrch(array, 3);

        // sorting ascending
        System.out.println("Arranged by Bubble Sort: " + Arrays.toString(array));
        System.out.println("Arranged by Insert Sort: " + Arrays.toString(array));
        System.out.println("Arranged by Selection Sort: " +Arrays.toString(array));

        // searching
        System.out.println();
        System.out.println("Linear Search: Element " + element + " found at index " + result1);
        System.out.println("Binary Search: Element " + element + " fount at index " + result2);
    }
}
