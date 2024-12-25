package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day12/input.txt"));

        List<List<String>> grid = reader.lines()
                .map(line -> Arrays.stream(line.split(""))
                        .toList())
                .toList();

        Set<String> uniquePlots = grid.stream()
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        partOne(grid, uniquePlots);
        partTwo(grid, uniquePlots);



        reader.close();
    }

    public static void partOne(List<List<String>> grid, Set<String> plots) {
        long total = 0;
        for (String plot : plots) {
            long curr = solveTotalForPlot(grid, plot);
            total += curr;
        }

        System.out.println(total);
    }

    public static void partTwo(List<List<String>> grid, Set<String> plots) {

    }



    private static long solveTotalForPlot(List<List<String>> grid, String plot) {
        Set<String> areaVisited = new HashSet<>();
        Set<String> perimeterVisited = new HashSet<>();
        int ROWS = grid.size();
        int COLS = grid.get(0).size();
        long total = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid.get(r).get(c).equals(plot)) {
                    int area = computeArea(grid, r, c, ROWS, COLS, areaVisited, plot);
                    long perimeter = computePerimeter(grid, r, c, ROWS, COLS, perimeterVisited, plot);
                    total += area * perimeter;
                }
            }
        }
        return total;
    }


    private static int computeArea(List<List<String>> grid, int r, int c, int ROWS, int COLS, Set<String> visited, String plot) {

        String key = r + "|" + c;
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS || visited.contains(key) || !grid.get(r).get(c).equals(plot)) {
            return 0;
        }

        visited.add(key);

         return 1 + computeArea(grid, r + 1, c, ROWS, COLS, visited, plot) +
                computeArea(grid, r - 1, c, ROWS, COLS, visited, plot) +
                computeArea(grid, r, c + 1, ROWS, COLS, visited, plot) +
                computeArea(grid, r, c - 1, ROWS, COLS, visited, plot);

    }


    private static int computePerimeter(List<List<String>> grid, int r, int c, int ROWS, int COLS, Set<String> visited, String plot) {
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS || !grid.get(r).get(c).equals(plot)) {
            return 1;
        }

        String key = r + "|" + c;
        if (visited.contains(key)) {
            return 0;
        }



        visited.add(key);

        return computePerimeter(grid, r+1, c, ROWS, COLS, visited, plot) +
                computePerimeter(grid, r-1, c, ROWS, COLS, visited, plot) +
                computePerimeter(grid, r, c+1, ROWS, COLS, visited, plot) +
                computePerimeter(grid, r, c-1, ROWS, COLS, visited, plot);


    }
}
