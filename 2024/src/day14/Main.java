package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/day14/input.txt"));

        List<List<Integer>> robots = new ArrayList<>();

        reader.lines()
            .forEach(line -> {
                String[] split = line.split(" ");
                List<Integer> p = new ArrayList<>(Arrays.stream(split[0].split("=")[1].split(",")).map(Integer::parseInt).toList());
                List<Integer> v = new ArrayList<>(Arrays.stream(split[1].split("=")[1].split(",")).map(Integer::parseInt).toList());
                p.addAll(v);
                robots.add(p);
                System.out.println(p);
            });

        int H = 103;
        int W = 101;

        partOne(robots, H, W);

        reader.close();
    }

    private static void partOne(List<List<Integer>> robots, int ROWS, int COLS){
        for (int i = 0; i < 100; i++) {
            for (List<Integer> robot : robots) {
                // 0 = pX, 1 = pY, 2 = vX, 3 = vY
                robot.set(0, robot.get(0) + robot.get(2));
                robot.set(1, robot.get(1) + robot.get(3));

                if (robot.get(0) >= COLS) {
                    robot.set(0, robot.get(0) - COLS);
                }
                if (robot.get(0) < 0) {
                    robot.set(0, COLS + robot.get(0));
                }

                if (robot.get(1) >= ROWS) {
                    robot.set(1, robot.get(1) - ROWS);
                }
                if (robot.get(1) < 0) {
                    robot.set(1, ROWS + robot.get(1));
                }

            }
        }


        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());

        for (List<Integer> robot : robots) {
            Integer X = robot.get(0);
            Integer Y = robot.get(1);
            int midCol = (COLS / 2);
            int midRow = (ROWS / 2);
            if (X < midCol && Y < midRow ) {
                res.get(0).add(1);
            }else if (X > midCol && Y < midRow) {
                res.get(1).add(1);
            }else if (X < midCol && Y > midRow) {
                res.get(2).add(1);
            }else if (X > midCol && Y > midRow) {
                res.get(3).add(1);
            }
        }

        Integer i = res.stream()
                .map(List::size)
                .reduce((a, b) -> a * b)
                .get();
        System.out.println(i);
    }
}
