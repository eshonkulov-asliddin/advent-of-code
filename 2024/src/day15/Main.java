package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader("src/day15/input.txt"));

        List<List<String>> grid = new ArrayList<>();
        StringBuilder moves = new StringBuilder();
        reader.lines()
            .forEach(line -> {
                if (line.isEmpty()) {

                } else if (line.startsWith("#")) {
                    List<String> gridLine = new ArrayList<>(Arrays.stream(line.split("")).toList());
                    grid.add(gridLine);
                }else {
                    moves.append(line);
                }
            });

        int ROWS = grid.size();
        int COLS = grid.get(0).size();

        int startR = -1;
        int startC = -1;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid.get(r).get(c).equals("@")) {
                    startR = r;
                    startC = c;
                    break;
                }
            }
        }

//        System.out.println(startR + "," +startC);
        System.out.println(grid);
        System.out.println(moves);


        for (char move : moves.toString().toCharArray()) {
            if (move == '<') {
                if (grid.get(startR).get(startC - 1).equals("#")) {
                    continue;
                }

                // find first dot
                boolean isDotFound = false;
                int p = startC - 1;
                while (p >= 0) {
                    String curr = grid.get(startR).get(p);
                    if (curr.equals(".")) {
                        isDotFound = true;
                        break;
                    }

                    if (curr.equals("#")) {
                        break;
                    }
                    p--;
                }

                if (isDotFound) {
                    while (p < startC) {
                        String nxt = grid.get(startR).get(p+1);
                        grid.get(startR).set(p, nxt);
                        if (nxt.equals("@")) {
                            startC = p;
                        }
                        p++;
                    }
                    grid.get(startR).set(p, ".");
                }


            } else if (move == '>') {
                if (grid.get(startR).get(startC + 1).equals("#")) {
                    continue;
                }

                // find first dot
                boolean isDotFound = false;
                int p = startC + 1;
                while (p < COLS) {
                    String curr = grid.get(startR).get(p);
                    if (curr.equals(".")) {
                        isDotFound = true;
                        break;
                    }

                    if (curr.equals("#")) {
                        break;
                    }
                    p++;
                }

                if (isDotFound) {
                    while (p > startC) {
                        String prv = grid.get(startR).get(p-1);
                        grid.get(startR).set(p, prv);
                        if (prv.equals("@")) {
                            startC = p;
                        }
                        p--;
                    }
                    grid.get(startR).set(p, ".");
                }

            } else if (move == '^') {
                if (grid.get(startR - 1).get(startC).equals("#")) {
                    continue;
                }

                // find first dot
                boolean isDotFound = false;
                int p = startR - 1;
                while (p >= 0) {
                    String curr = grid.get(p).get(startC);
                    if (curr.equals(".")) {
                        isDotFound = true;
                        break;
                    }

                    if (curr.equals("#")) {
                        break;
                    }
                    p--;
                }

                if (isDotFound) {
                    while (p < startR) {
                        String nxt = grid.get(p+1).get(startC);
                        grid.get(p).set(startC, nxt);
                        if (nxt.equals("@")) {
                            startR = p;
                        }
                        p++;
                    }
                    grid.get(p).set(startC, ".");
                }
            }else {
                if (grid.get(startR + 1).get(startC).equals("#")) {
                    continue;
                }

                // find first dot
                boolean isDotFound = false;
                int p = startR + 1;
                while (p < ROWS) {
                    String curr = grid.get(p).get(startC);
                    if (curr.equals(".")) {
                        isDotFound = true;
                        break;
                    }

                    if (curr.equals("#")) {
                        break;
                    }
                    p++;
                }

                if (isDotFound) {
                    while (p > startR) {
                        String prv = grid.get(p-1).get(startC);
                        grid.get(p).set(startC, prv);
                        if (prv.equals("@")) {
                            startR = p;
                        }
                        p--;
                    }
                    grid.get(p).set(startC, ".");
                }

            }
        }

        grid.forEach(System.out::println);

        long GPS = 0;
        for (int r = 1; r < ROWS; r++) {
            for (int c = 1; c < COLS; c++) {
                if (grid.get(r).get(c).equals("O")) {
                    GPS += 100L * r + c;
                }
            }
        }

        System.out.println(GPS);
        reader.close();

    }

}
