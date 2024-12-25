package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Main {

    // partOne const
    private static final String WORD = "XMAS";
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    // partTwo const


    public static void main(String[] args) throws IOException {

        List<String> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("src/day04/input.txt"));

        String line;
        while ( (line = reader.readLine()) != null) {
            lines.add(line);
        }

        char[][] grid = lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        partTwo(grid);


    }

    private static void partTwo(char[][] grid) {
        int res = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'S' || grid[r][c] == 'M') {

                    // right pairs
                    Pair p1R = new Pair(r, c);
                    Pair p2R = new Pair(r+1, c+1);
                    Pair p3R = new Pair(r+2, c+2);

                    // left pairs
                    Pair p1L = new Pair(r, c+2);
                    Pair p2L = new Pair(r+1, c+1);
                    Pair p3L = new Pair(r+2, c);

                    boolean isValid = validateBoundaries(List.of(p1R, p2R, p2R, p1L, p2L, p3L), grid.length, grid[0].length);

                    if (isValid) {
                        res += validateDiagonal(p1R, p2R, p3R, grid) && validateDiagonal(p1L, p2L, p3L, grid) ? 1 : 0;
                    }
                }
            }
        }
        System.out.println(res);
    }


    private static boolean validateBoundaries(List<Pair> pairs, int ROWS, int COLS) {

        return  pairs.stream().allMatch(p -> (p.r >= 0 && p.r < ROWS) && (p.c >= 0 && p.c < COLS));
    }

    private static boolean validateDiagonal(Pair first, Pair second, Pair third, char[][] grid) {

        char firstCh = grid[first.r][first.c];
        char secondCh = grid[second.r][second.c];
        char thirdCh = grid[third.r][third.c];

        if (firstCh == 'S') {
            return secondCh == 'A' && thirdCh == 'M';
        }else if (firstCh == 'M') {
            return secondCh == 'A' && thirdCh == 'S';
        }else {
            return false;
        }
    }

    private static void partOne(char[][] grid){
        int total = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                total += findWords(grid, r, c, 0, 0, new HashSet<>());
            }
        }

        System.out.println(total);
    }

    private static int findWords(char[][] grid, int r, int c, int index, int directionIndex, Set<Pair> seen) {
        if (index >= WORD.length()) {
            return 1; // Successfully found "XMAS"
        }
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen.contains(new Pair(r, c))) {
            return 0;
        }

        if (grid[r][c] != WORD.charAt(index)) {
            return 0;
        }

        seen.add(new Pair(r, c));
        int count = 0;

        if (index == 0) { // Start new directions from the first character 'X'
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int nextR = r + DIRECTIONS[i][0];
                int nextC = c + DIRECTIONS[i][1];
                count += findWords(grid, nextR, nextC, index + 1, i, new HashSet<>(seen));
            }
        } else {
            int nextR = r + DIRECTIONS[directionIndex][0];
            int nextC = c + DIRECTIONS[directionIndex][1];
            count += findWords(grid, nextR, nextC, index + 1, directionIndex, seen);
        }

        seen.remove(new Pair(r, c));
        return count;
    }

}


class Pair {
    int r;
    int c;

    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair pair)) return false;
        return r == pair.r && c == pair.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}