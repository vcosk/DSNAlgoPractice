package in.vikk.dailycoding;

import java.util.Arrays;

//  Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
//  For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
//  Bonus: Can you do this in one pass?

public class PairedSum {

    public static boolean pairedSum(int[] numbers, int sum) {
        Arrays.sort(numbers);
        for (int smallerNumber = 0, largerNumber = numbers.length - 1; smallerNumber < largerNumber;) {
            int currentSum = numbers[smallerNumber] + numbers[largerNumber];
            if (currentSum == sum) {
                return true;
            } else if (currentSum < sum) {
                smallerNumber += 1;
            } else {
                largerNumber -= 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(pairedSum(new int[]{
                10, 15, 3, 7
        }, 18));

        System.out.println(pairedSum(new int[]{
                10, 15, 3, 7
        }, 15));
    }
}
