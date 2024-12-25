package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static int DIRECTION = 0;
    static Set<String> hashTag = new HashSet<>();
    static Set<String> visitedPaths = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day06/input"));

        List<String> lines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        char[][] grid = lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int[] rc = findStart(grid);
        partOne(grid, rc[0], rc[1], new HashSet<>());
        partTwo(grid, rc[0], rc[1]);
    }


    private static void partTwo(char[][] grid, int startR, int startC) {
        int cnt = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
//                System.out.println(visitedPaths);
                if (grid[r][c] == '.') {
                    char[][] newGrid = new char[grid.length][grid[0].length];
                    for (int i = 0; i < grid.length; i++) {
                         newGrid[i] = grid[i].clone();
                    }
                    newGrid[r][c] = '#';
                    DIRECTION = 0;
                    cnt += isLoopExist(newGrid, startR, startC, new HashSet<>()) ? 1 : 0;
                }
            }
        }

        System.out.println(cnt);
    }

    private static boolean isLoopExist(char[][] grid, int r, int c, Set<String> visited) {
        while (true) {
            // Check if this position with the current direction has already been visited
            String key = r + "," + c + "," + DIRECTION;
            if (visited.contains(key)) {
                return true; // Found a loop
            }

            // Mark the current position with the current direction as visited
            visited.add(key);

            // If there's a wall ('#') in the current direction, update direction (rotate 90 degrees)
            if (DIRECTION == 0 && r > 0 && grid[r-1][c] == '#') {
                updateDirection();
            } else if (DIRECTION == 90 && c < grid[0].length - 1 && grid[r][c+1] == '#') {
                updateDirection();
            } else if (DIRECTION == 180 && r < grid.length - 1 && grid[r+1][c] == '#') {
                updateDirection();
            } else if (DIRECTION == 270 && c > 0 && grid[r][c-1] == '#') {
                updateDirection();
            }

            // Move the guard in the current direction
            if (DIRECTION == 0) {
                r--;  // Move up
            } else if (DIRECTION == 90) {
                c++;  // Move right
            } else if (DIRECTION == 180) {
                r++;  // Move down
            } else if (DIRECTION == 270) {
                c--;  // Move left
            }

            // Boundary check: If the guard goes out of bounds, stop the loop
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
                break;  // Stop if the guard moves outside the grid
            }
        }

        return false; // No loop detected, guard moved out of bounds
    }

    private static int[] findStart(char[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '^'){
                    return new int[] {r, c};
                }
            }
        }
        return new int[] {-1, -1};
    }

    private static int partOne(char[][] grid, int r, int c,  Set<String> visited) {

        while (!(r < 0 || r >= grid.length - 1 || c < 0 || c >= grid[0].length - 1)){
            if (DIRECTION == 0 && grid[r-1][c] == '#') {
                hashTag.add(r-1 + "|" + c);
                updateDirection();
            }else if (DIRECTION == 90 && grid[r][c+1] == '#') {
                hashTag.add(r + "|" + (c+1));
                updateDirection();
            }else if (DIRECTION == 180 && grid[r+1][c] == '#') {
                hashTag.add(r+1 + "|" + c);
                updateDirection();
            }else if (DIRECTION == 270 && grid[r][c-1] == '#'){
                hashTag.add(r+ "|" + (c-1));
                updateDirection();
            }

            visited.add(r + "," + c);
            visitedPaths.add(r + "," + c);

            if (DIRECTION == 0) {
                r -= 1;
            } else if (DIRECTION == 90) {
                c += 1;
            }else if (DIRECTION == 180){
                r += 1;
            }else {
                c -= 1;
            }
        }

        return visited.size() + 1;
    }

    private static void updateDirection() {
        DIRECTION += 90;
        if (DIRECTION == 360) {
            DIRECTION = 0;
        }
    }
}

