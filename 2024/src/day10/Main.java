package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day10/input.txt"));

        List<List<Integer>> list = reader.lines()
                .map(line -> Arrays.stream(line.split(""))
                    .map(Integer::parseInt)
                    .toList())
                .toList();

        partOne(list);
        partTwo(list);

        reader.close();
    }

    private static void partOne(List<List<Integer>> grid) {
        int total = 0;
        int ROWS = grid.size();
        int COLS = grid.get(0).size();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid.get(r).get(c) == 0) {
                    Set<String> res = new HashSet<>();
                    dfs(grid, r, c, grid.size(), grid.get(0).size(), new HashSet<>(), res);
                    total += res.size();
                }
            }
        }

        System.out.println("Part One: " + total);


    }

    private static void partTwo(List<List<Integer>> grid) {
        int total = 0;
        int ROWS = grid.size();
        int COLS = grid.get(0).size();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid.get(r).get(c) == 0) {
                    Set<String> res = new HashSet<>();
                    dfs(grid, r, c, grid.size(), grid.get(0).size(), new HashSet<>(), res,  new StringBuilder());
                    total += res.size();
                }
            }
        }

        System.out.println("Part Two: " + total);


    }

    private static void dfs(List<List<Integer>> grid, int r, int c, int ROWS, int COLS, Set<String> visited, Set<String> res) {

        String key = String.format("%s#%s,", r, c);

        if (r < 0 || r >= ROWS || c < 0 || c >= COLS || visited.contains(key)) {
            return;
        }

        int curr = grid.get(r).get(c);

        visited.add(key);

        if (curr == 9) {
            res.add(key);
            return;
        }

        if (r - 1 >= 0) {
            int top = grid.get(r-1).get(c);
            if (top - curr == 1) {
               dfs(grid, r-1, c, ROWS, COLS, visited, res);
            }
        }

        if (r + 1 < ROWS) {
            int bottom = grid.get(r+1).get(c);
            if (bottom - curr == 1) {
                dfs(grid, r + 1, c, ROWS, COLS, visited, res);
            }
        }

        if (c - 1 >= 0) {
            int left = grid.get(r).get(c-1);
            if (left - curr == 1) {
                dfs(grid, r, c-1, ROWS, COLS, visited, res);
            }
        }

        if (c + 1 < COLS) {
            int right = grid.get(r).get(c+1);
            if (right - curr == 1) {
                dfs(grid, r, c+1, ROWS, COLS, visited, res);
            }
        }

    }

    private static void dfs(List<List<Integer>> grid, int r, int c, int ROWS, int COLS, Set<String> visited, Set<String> res, StringBuilder trail) {

        String key = String.format("%s#%s|", r, c);

        if (r < 0 || r >= ROWS || c < 0 || c >= COLS || visited.contains(key)) {
            return;
        }

        visited.add(key);
        int curr = grid.get(r).get(c);
        StringBuilder newTrail = new StringBuilder(trail).append(key);


        if (curr == 9) {
            res.add(newTrail.toString());
            visited.remove(key);
            return;
        }


        if (r - 1 >= 0) {
            int top = grid.get(r-1).get(c);
            if (top - curr == 1) {
                dfs(grid, r-1, c, ROWS, COLS, visited, res, new StringBuilder(newTrail));
            }
        }

        if (r + 1 < ROWS) {
            int bottom = grid.get(r+1).get(c);
            if (bottom - curr == 1) {

                dfs(grid, r + 1, c, ROWS, COLS, visited, res, new StringBuilder(newTrail));
            }
        }

        if (c - 1 >= 0) {
            int left = grid.get(r).get(c-1);
            if (left - curr == 1) {
                dfs(grid, r, c-1, ROWS, COLS, visited, res, new StringBuilder(newTrail));
            }
        }

        if (c + 1 < COLS) {
            int right = grid.get(r).get(c+1);
            if (right - curr == 1) {
                dfs(grid, r, c+1, ROWS, COLS, visited, res, new StringBuilder(newTrail));
            }
        }

        visited.remove(key);
    }
}
