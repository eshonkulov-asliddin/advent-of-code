package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/day07/input.txt"));

        long totalSum = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(": ");
            long target = Long.parseLong(split[0]);
            List<Long> numbers = Arrays.stream(split[1].split(" ")).map(Long::parseLong).toList();
            Set<Long> res = new HashSet<>();

            if (!numbers.isEmpty()) {
                isValid(target, numbers.subList(1, numbers.size()), numbers.get(0), res);
            }

            if (res.contains(target)) {
                totalSum += target;
            }
        }

        System.out.println(totalSum);
        reader.close();
    }

    private static void isValid(long target, List<Long> numbers, long curr, Set<Long> res) {
        if (numbers.isEmpty()) {
            res.add(curr);
            return;
        }

        long head = numbers.get(0);
        List<Long> tail = numbers.subList(1, numbers.size());

        isValid(target, tail, curr + head, res);
        isValid(target, tail, curr * head, res);
        isValid(target, tail, Long.parseLong(curr + "" + head), res);
    }
}
//6231007346736