package in.vikk.hackerrank.ipk.util;

import in.vikk.hackerrank.ipk.arrays.NewYearChaos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Utility {

    public static int[] readIntArrayFromFile(String fileName) {
        int[] data = null;
        try (
                InputStreamReader isr = new InputStreamReader(NewYearChaos.class.getClassLoader().getResourceAsStream(fileName));
                BufferedReader br = new BufferedReader(isr)
        ) {
            String[] dataStrArray = br.readLine().trim().split(" ");
            data = new int[dataStrArray.length];
            for (int index = 0; index < dataStrArray.length; index++) {
                data[index] = Integer.parseInt(dataStrArray[index]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int[][] readInt2DArrayFromFile(String fileName) {
        int[][] data = null;
        try (
                InputStreamReader isr = new InputStreamReader(Utility.class.getClassLoader().getResourceAsStream(fileName));
                BufferedReader br = new BufferedReader(isr)
        ) {
            List<int[]> rows = new LinkedList<>();
            while (br.ready()) {
                String[] dataStrArray = br.readLine().trim().split(" ");
                int[] rowData = new int[dataStrArray.length];
                for (int index = 0; index < dataStrArray.length; index++) {
                    rowData[index] = Integer.parseInt(dataStrArray[index]);
                }
                rows.add(rowData);
            }
            data = rows.toArray(new int[][]{});
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
