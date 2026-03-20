import java.util.Arrays;

class Oikawa {

    int size = 0;

    // bubble sort
    public void BubbleSort (int array []) {
        size = array.length;

        // process
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array [j] > array[j + 1]) { // (array [j] < array[j + 1]) - DESCENDING
                    int temp = array [j];
                    array [j] = array [j + 1];
                    array [j + 1] = temp;
                }

            }
        }
    }

    // insertion
    public void InsertSort (int array []) {
        size = array.length;

        // process

        for (int i = 1; i < size; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) { // (j >= 0 && key > array[j]) - DESCENDING
                array [j + 1] = array[j];
                --j;
            }
            array [j + 1] = key;
        }

    }

    // selection
    public void SelectionSort (int array[]) {
        size = array.length;

        // process
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[min]) { // (array[j] > array[min]) - DESCENDING
                    min = j; // fixed to j
                }

            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }

    }

    // Linear Search
    public int LinearSearch (int[] array, int key) {
        size = array.length;

        // process
        for (int i = 0; i < size; i++) {
            if (array[i] == key) {
                return i; // returns index
            }
        }
        return - 1; // not found
    }

    // Binary Search
    public int BinarySearch(int[] array, int key) {
        size = array.length;
        int left = 0, right = size - 1;

        // process
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                return mid; // found
            }

            if (array[mid] < key) { // (array[mid] > key) - DESCENDING
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // not found
    }
}



public class iwa {

    public static void main(String[] args) {

        System.out.println("Original list: ");
        int[] array = {2, 5, 9, 8, 7, 1, 3, 4, 6};

        // Print og list
        for (int i : array)
            System.out.print(i + " ");

        System.out.println("\n");

        // oop
        Oikawa oikawa = new Oikawa();

        System.out.println("\nSearching: ");

        // Linear Search (unsorted array)
        System.out.println("Linear Search: ");
        int result = oikawa.LinearSearch(array, 9);

        // if else rana uy kay sayun -_-
        if (result != -1) {
            System.out.println("Key found at index (unsorted): " + result);
        } else {
            System.out.println("Key not found");
        }

        // now apply sorting on the same array
        System.out.println("\nBubble sort: ");
        oikawa.BubbleSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Insertion sort: ");
        oikawa.InsertSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Selection sort: ");
        oikawa.SelectionSort(array);
        System.out.println(Arrays.toString(array));

        // Binary Search (requires sorted array)
        System.out.println("\nBinary Search: ");
        int resultBin = oikawa.BinarySearch(array, 9);

        if (resultBin != -1) {
            System.out.println("Key found at index (sorted): " + resultBin);
        } else {
            System.out.println("Key not found");
        }

    }
}
