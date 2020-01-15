package in.vikk.sort;

import java.util.Arrays;

/**
 * Class sorts data usingInsertion sort.
 */
public class InsertionSort {
//    /**
//     * Performs inline sort of the specified data.
//     * @param data Array of data to be sorted.
//     */
//    public static <T extends Comparable> void sort(T[] data) {
//        for (int j = 1; j < data.length; j++) {
//            T key = data[j];
//            int i = j - 1;
//            while (i > -1 && data[i].compareTo(key) > 0) {
//                data[j] = data[i];
//                i = i - 1;
//            }
//            data[i + 1] = key;
//        }
//    }

    public static <T extends Comparable> void sort(T[] data) {
        for (int i = 1; i < data.length; i++) {
            int key = i;
            for (int j = key - 1; j >= 0
                    && data[j].compareTo(data[key]) > 0; j--) {
                T temp = data[j];
                data[j] = data[key];
                data[key] = temp;
                key = j;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] data = {9,8,7,6,555,4,3,2,1,10};
        System.out.println(Arrays.toString(data));
        InsertionSort.sort(data);
        System.out.println(Arrays.toString(data));
    }
}
