package in.vikk.hackerrank.ipk.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

/**
 * Prefix sum arrays and Difference arrays: <a href="https://wcipeg.com/wiki/Prefix_sum_array_and_difference_array#Use_of_difference_array">
 *     PEGWiki</a>
 * Range Update Arrays: <a href="https://www.geeksforgeeks.org/difference-array-range-update-query-o1/">Geeks For Geeks</a>.
 */

public class ArrayManipulation {

    public static void main(String[] args) {
        // 10
        test("in/vikk/hackerrank/ipk/testcases/ArrayManipulation/1.txt");
        // 200
        test("in/vikk/hackerrank/ipk/testcases/ArrayManipulation/2.txt");
        // 7515267971
        test("in/vikk/hackerrank/ipk/testcases/ArrayManipulation/3.txt");
        // 2506721627
        test("in/vikk/hackerrank/ipk/testcases/ArrayManipulation/4.txt");
    }

    private static void test(String fileName) {
        try (
                InputStream is = ArrayManipulation.class
                        .getClassLoader().getResourceAsStream(fileName);
                InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
        ) {
            if (br.ready()) {
                long startTime = new Date().getTime();
                String[] numbers = br.readLine().split(" ");
                int arrayLength = Integer.parseInt(numbers[0]);
                int operationCount = Integer.parseInt(numbers[1]);
                long[] differenceArray = new long[arrayLength];
                Arrays.fill(differenceArray, 0);
                for (int index = 0; index < operationCount; index++) {
                    String[] operationsStr = br.readLine().split(" ");
                    int[] operation  = new int[operationCount];
                    for (int strIndex = 0; strIndex < operationsStr.length; strIndex++) {
                        operation[strIndex] = Integer.parseInt(operationsStr[strIndex]);
                    }
                    differenceArray[operation[0] - 1] += operation[2];
                    if (operation[1] < differenceArray.length) {
                        differenceArray[operation[1]] -= operation[2];
                    }
                }

                long max = differenceArray[0];
                long prevoius = max;
                for (int index = 1; index < differenceArray.length; index++) {

                    long current = differenceArray[index] + prevoius;
                    if (max < current) {
                        max = current;
                    }
                    prevoius = current;
                }

                System.out.println(max);
                System.out.println("Execution Time: " + (new Date().getTime() - startTime) / 1000);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
