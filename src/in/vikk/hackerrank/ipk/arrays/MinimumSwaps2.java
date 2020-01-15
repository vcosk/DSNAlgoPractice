package in.vikk.hackerrank.ipk.arrays;

import in.vikk.hackerrank.ipk.util.Utility;

import java.util.Date;

public class MinimumSwaps2 {

    static int minimumSwaps(int[] arr) {
        int swapCounter = 0;
        int smallestNumberIndex = 0;
        for (int index = 1; index < arr.length; index++) {
            if (arr[smallestNumberIndex] > arr[index]) {
                smallestNumberIndex = index;
            }
        }
        if (smallestNumberIndex != 0) {
            int temp = arr[smallestNumberIndex];
            arr[smallestNumberIndex] = arr[0];
            arr[0] = temp;
            swapCounter += 1;
        }

        int indexIncrementor = arr[0];
        int elementCounter = 1, index = 1;
        for (; elementCounter <= arr.length && index < arr.length; ) {
            int delta = arr[index] - indexIncrementor;
            if (delta == index) {
                index += 1;
                elementCounter++;
            }
            else {
                int temp = arr[index];
                arr[index] = arr[delta];
                arr[delta] = temp;
                swapCounter += 1;
            }
        }
        return swapCounter;
    }

    public static void main(String[] args) {
        // 5
        test("in/vikk/hackerrank/ipk/testcases/MinimumSwaps2/1.txt");

        // 3
        test("in/vikk/hackerrank/ipk/testcases/MinimumSwaps2/2.txt");

        // 3
        test("in/vikk/hackerrank/ipk/testcases/MinimumSwaps2/3.txt");

        // 3
        test("in/vikk/hackerrank/ipk/testcases/MinimumSwaps2/4.txt");

        // 99984
        test("in/vikk/hackerrank/ipk/testcases/MinimumSwaps2/5.txt");
    }

    private static void test(String fileName) {
        int[] a = Utility.readIntArrayFromFile(fileName);
        long startTime = new Date().getTime();
        System.out.println(minimumSwaps(a));
        System.out.println("Execution Time: " + (new Date().getTime() - startTime) / 1000);
    }
}
