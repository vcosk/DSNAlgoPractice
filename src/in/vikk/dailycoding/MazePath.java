package in.vikk.dailycoding;

import com.sun.istack.internal.NotNull;
import in.vikk.hackerrank.ipk.util.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiConsumer;

public class MazePath {

    private static class MazeCell {

        public MazeCell(int x, int y, boolean wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }

        private int x;

        private int y;

        private boolean wall = false;

        private boolean visited = false;

        @Override
        public String toString() {
            return "MazeCell{" +
                    "x=" + x +
                    ", y=" + y +
                    ", wall=" + wall +
                    '}';
        }
    }

    public static List<MazeCell> findPath(@NotNull MazeCell[][] maze, @NotNull int[] startPos, @NotNull int[] endPos) {
        LinkedList<MazeCell> path = new LinkedList<>();
        BiConsumer<int[], String> positionValidationHelper = (position, message) -> {
            if (!(position.length == 2 && position[0] < maze.length && position[1] < maze[0].length)) {
                throw new IllegalArgumentException(message);
            }
        };

        positionValidationHelper.accept(startPos, "Invalid start position.");
        positionValidationHelper.accept(startPos, "Invalid end position.");

        return path;
    }

    private static boolean pathExists(MazeCell[][] maze, int row, int col, int[] endPos, LinkedList<MazeCell> path) {
        MazeCell testCell = maze[row][col];
        testCell.visited = true;
        // Found
        if (testCell.x == endPos[0] && testCell.y == endPos[1]) {
            return true;
        }
        else {
            
        }
        return false;
    }

    public static void main(String[] args) {
        test("in/vikk/dailycoding/testcases/MazePath/1.txt");
    }

    private static void test(@NotNull String fileName) {
        try (
                InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(Utility.class.getClassLoader().getResourceAsStream(fileName)));
                BufferedReader br = new BufferedReader(isr)
        ) {
            String[] mazeSizeStr = br.readLine().trim().split(" ");
            String[] startPosStr = br.readLine().trim().split(" ");
            String[] endPosStr = br.readLine().trim().split(" ");
            int[] mazeSize = new int[] {Integer.parseInt(mazeSizeStr[0]), Integer.parseInt(mazeSizeStr[1])};
            int[] startPos = new int[] {Integer.parseInt(startPosStr[0]), Integer.parseInt(startPosStr[1])};
            int[] endPos = new int[] {Integer.parseInt(endPosStr[0]), Integer.parseInt(endPosStr[1])};

            MazeCell[][] maze = new MazeCell[mazeSize[0]][mazeSize[1]];

            for (int index = 0; index < mazeSize[0]; index++) {
                String[] mazeEntries = br.readLine().trim().split(" ");
                for (int column = 0; column < mazeEntries.length; column++) {
                    maze[index][column] = new MazeCell(index, column, Integer.parseInt(mazeEntries[column]) == 0);
                }
            }

            List<MazeCell> path = findPath(Objects.requireNonNull(maze),
                    Objects.requireNonNull(startPos),
                    Objects.requireNonNull(endPos));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
