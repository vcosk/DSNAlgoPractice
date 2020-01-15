package in.vikk.hackerrank.ipk.arrays;

public class HourGlassSum {
    private static final int indexes [][] = new int[][] {
            {0, 0}, {0, 1},{0, 2},
            {1, 1},
            {2, 0}, {2, 1}, {2, 2}
    };
    private static final int hourGlassFactor = 2;
    static int hourglassSum(int[][] arr) {
        int[][] hourGlassArray = new int[arr.length - hourGlassFactor][];
        int maxHourGlassSum = Integer.MIN_VALUE;
        for (int rowIndex = 0; rowIndex < arr.length - hourGlassFactor; rowIndex++) {
            hourGlassArray[rowIndex] =  new int[arr[rowIndex].length - hourGlassFactor];
            for (int columnIndex = 0; columnIndex < arr[rowIndex].length - hourGlassFactor; columnIndex++) {
                int hourGalssSum = 0;
                for (int[] index : indexes) {
                    hourGalssSum += arr[rowIndex + index[0]][columnIndex + index[1]];
                }
                if (hourGalssSum > maxHourGlassSum) {
                    maxHourGlassSum = hourGalssSum;
                }
                hourGlassArray[rowIndex][columnIndex] = hourGalssSum;
            }
        }
        return maxHourGlassSum;
    }

    public static void main(String[] args) {
        System.out.println(hourglassSum(new int[][] {
                {-9,-9,-9,1,1,1},
                {0,-9,0,4,3,2},
                {-9,-9,-9,1,2,3},
                {0,0,8,6,6,0},
                {0,0,0,-2,0,0},
                {0,0,1,2,4,0},
        }));

//        System.out.println(hourglassSum(new int[][] {
//                {}
//        }));
    }
}
