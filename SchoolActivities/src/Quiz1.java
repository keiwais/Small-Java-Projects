import java.util.Arrays;

class BookCodes {

    // bubble sort
    public void BubbleSort (int array []) {
        int size = array.length;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public int LinearSearch (int [] array, int index) {
        int size = array.length;

        for (int i = 0; i < size; i++) {
            if (array[i] == index) {
                return i;
            }
        }
        return -1;
    }

}

public class Quiz1 {

    public static void main(String[] args) {

        // arrays
        int [] arrays = {45, 12, 78, 34, 23, 56, 89, 10, 67, 90, 45, 56};

        // create object
        BookCodes book = new BookCodes();
        BookCodes books = new BookCodes();
        book.BubbleSort(arrays);

        // arrange using bubble sort
        System.out.println("Arrays arranged using Bubble Sort:");
        for (int i : arrays) {
            System.out.print(i + " ");
        }

        System.out.println();


        // without the duplicates
        System.out.println();
        System.out.println("\nArrays without duplicate sorted: ");

        // finding duplicates
        int temp = arrays[5];
        arrays[6] = temp;
        arrays[6] = arrays[7];
        arrays[5] = arrays[6];
        arrays[7] = arrays[8];
        arrays[6] = arrays[7];
        arrays[8] = arrays[9];
        arrays[7] = arrays[8];
        arrays[9] = arrays[10];
        arrays[8] = arrays[9];
        arrays[10] = arrays[11];
        arrays[9] = arrays[10];

        // display
        for (int i = 0; i < arrays.length - 2; i++){
            System.out.print(arrays[i] + " ");
        }

        System.out.println();

        // search for a specific index
        int index = 0;
        int result = 0;
        int result1 = 0;

        System.out.println("\nSearching number 12 ...");
        index = 12;
        result = book.LinearSearch(arrays, index);

        // display
        if (result != -1) {
            System.out.println("Number found at index " + result);
        } else {
            System.out.println("Number not found, number is not in the list.");
        }

        System.out.println("\nSearching number 100 ...");
        index = 100;
        result1 = books.LinearSearch(arrays, index);

        if (result1 != -1) {
            System.out.println("Number found at index " + result);
        } else {
            System.out.println("Number not found, number is not in the list.");
        }
    }
}
