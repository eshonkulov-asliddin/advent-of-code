package day24;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/day24/input.txt"));

        Map<String, Integer> inputs = new TreeMap<>();
        Deque<String> ops = new LinkedList<>();

        AtomicBoolean isInput = new AtomicBoolean(true);

        reader.lines()
            .forEach(line -> {

                if (!line.isEmpty() && isInput.get()) {
                    String[] arr = line.split(":");
                    inputs.put(arr[0], Integer.valueOf(arr[1].trim()));
                }

                if (!line.isEmpty() && !isInput.get()) {
                    ops.add(line);
                }

                if (line.isEmpty()) {
                    isInput.set(false);
                }
            });

        partOne(ops, inputs);


        reader.close();

    }

    private static void partOne(Deque<String> ops, Map<String, Integer> inputs) {
        while (!ops.isEmpty()) {

            String op = ops.pollFirst();
            String[] arr = op.split(" ");
            String f = arr[0];
            String operator = arr[1];
            String s = arr[2];
            String res = arr[4];

            if (inputs.containsKey(f) && inputs.containsKey(s)) {
                int out = execute(inputs.get(f), inputs.get(s), operator);
                inputs.put(res, out);
            }else {
                ops.add(op);
            }

        }

        List<Integer> binaries = inputs.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .map(Map.Entry::getValue)
                .toList();

        long res = binaryToDecimal(binaries);

        System.out.println(res);
        List<String> list = inputs.keySet().stream().sorted().toList();
        System.out.println(list);
    }

    private static long binaryToDecimal(List<Integer> binaries) {
        long res = 0;
        long step = 1;
        for (int i = 0; i < binaries.size(); i++) {
            if (binaries.get(i) == 1) {
                res += step;
            }
            step *= 2;
        }
        return res;
    }

    private static int execute(int f, int s, String operator) {
        switch (operator) {
            case "XOR":
                return f != s ? 1 : 0;
            case "OR":
                return f == 1 || s == 1 ? 1 : 0;
            case "AND":
                return f == 1 && s == 1 ? 1 : 0;
            default:
                return 0;
        }
    }
}
