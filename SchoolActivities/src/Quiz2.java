import java.util.Arrays;
import java.util.Scanner;

class BookCode {

    public void InsertionSort (int []arrays ) {
        int size = arrays.length;
        for (int i = 1; i < size; i++) {
            int key = arrays[i];
            int j = i - 1;

            while (j >= 0 && key < arrays[j]) {
                arrays[j + 1] = arrays[j];
                --j;
            }
            arrays [j+1] = arrays[j];
        }
    }

    public int BinarySearch (int []arrays, int key) {
        int size = arrays.length;
        int start = 0;
        int end = size -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == arrays[mid]) {
                return mid;
            }
            if (key < arrays[mid]) {
                end = mid -1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }

}

public class Quiz2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numbers = 0;
        int i = 0;
        int j = 0;
        int[] arrays = new int[10];

        System.out.println("Input number : ");
        while (i < 10) {
            numbers = scanner.nextInt();
            arrays = new int[numbers];
            i++;
        }


        // new object
        BookCode book = new BookCode();

        // arrange using insertion
        book.InsertionSort(arrays);
        System.out.println("Arranged using Insertion sort: ");
        for (i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i] + " ");
        }

        // Binary search
        // not finished
    }
}
