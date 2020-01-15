package in.vikk.hackerrank.ipk.arrays;

import java.util.Arrays;

public class LeftRotate {
    static int[] rotLeft(int[] a, int d) {
        int[] rotatedArray = new int[a.length];
        for (int index = 0; index < a.length; index++) {
            int newIndex = index - d;
            if (newIndex < 0) {
                newIndex = a.length + newIndex;
            }
            else if (newIndex >= a.length) {
                newIndex = newIndex % a.length;
            }
            rotatedArray[newIndex] = a[index];
        }
        return rotatedArray;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotLeft(new int[]{
            1,2,3,4,5
        }, 4)));

        System.out.println(Arrays.toString(rotLeft(new int[]{
                1,2,3,4,5
        }, 2)));

        System.out.println(Arrays.toString(rotLeft(new int[]{
                1,2,3,4,5
        }, -2)));
    }
}
