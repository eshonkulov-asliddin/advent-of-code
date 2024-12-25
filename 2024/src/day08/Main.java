package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day08/input.txt"));

        List<String> lines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        char[][] array = lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        Map<Character, Set<String>> lookup = lookup(array);
        partTwo(lookup, array.length, array[0].length);

        reader.close();
    }

    private static Map<Character, Set<String>> lookup(char[][] grid) {
        Map<Character, Set<String>> res = new HashMap<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] != '.') {
                    res.putIfAbsent(grid[r][c], new HashSet<>());
                    res.get(grid[r][c]).add(r+","+c);
                }
            }
        }

        return res;
    }

    private static String minus(String v1, String v2) {
        String[] vec1 = v1.split(",");
        String[] vec2 = v2.split(",");

        int v3A = Integer.parseInt(vec1[0]) - Integer.parseInt(vec2[0]);
        int v3B = Integer.parseInt(vec1[1]) - Integer.parseInt(vec2[1]);

        return v3A + "," + v3B;
    }

    private static String plus(String v1, String v2) {
        String[] vec1 = v1.split(",");
        String[] vec2 = v2.split(",");

        int v3A = Integer.parseInt(vec1[0]) + Integer.parseInt(vec2[0]);
        int v3B = Integer.parseInt(vec1[1]) + Integer.parseInt(vec2[1]);

        return v3A + "," + v3B;
    }

    private static String multiply(String v1, int scalar) {
        String[] vec1 = v1.split(",");

        int v3A = Integer.parseInt(vec1[0]) * scalar;
        int v3B = Integer.parseInt(vec1[1]) * scalar;

        return v3A + "," + v3B;
    }

    private static boolean isBound(String vec, int ROWS, int COLS) {
        String[] v = vec.split(",");
        int x = Integer.parseInt(v[0]);
        int y = Integer.parseInt(v[1]);

        return x >= 0 && y >= 0 && x < COLS && y < ROWS;
    }

    private static void partOne(Map<Character, Set<String>> lookup, int ROWS, int COLS) {
        Set<String> result = new HashSet<>();
        for (Character type : lookup.keySet()) {
            for (var first : lookup.get(type)) {
                for (var second : lookup.get(type)) {
                    if (first == second) {
                        continue;
                    }

                    var distance = minus(second, first);
                    var realDistance = multiply(distance, 2);
                    var antinode = plus(first, realDistance);
                    if (isBound(antinode, ROWS, COLS)) {
                        result.add(antinode);
                    }
                }
            }
        }

        System.out.println(result.size());
    }

    private static void partTwo(Map<Character, Set<String>> lookup, int ROWS, int COLS) {
        Set<String> result = new HashSet<>();
        for (Character type : lookup.keySet()) {
            for (var first : lookup.get(type)) {
                for (var second : lookup.get(type)) {
                    if (first == second) {
                        continue;
                    }

                    var distance = minus(second, first);
                    var realDistance = distance;
                    var positiveNextLocation = first;

                    while (true) {
                        positiveNextLocation = plus(positiveNextLocation, realDistance);

                        if (!isBound(positiveNextLocation, ROWS, COLS)) {
                            break;
                        }
                        result.add(positiveNextLocation);
                    }

                    var negativeNextLocation = first;

                    while (true) {
                        negativeNextLocation = minus(negativeNextLocation, realDistance);

                        if (!isBound(negativeNextLocation, ROWS, COLS)) {
                            break;
                        }
                        result.add(negativeNextLocation);
                    }

                }
            }
        }

        System.out.println(result.size());
    }
}
