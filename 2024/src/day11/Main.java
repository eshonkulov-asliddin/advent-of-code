package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/day11/input.txt"));
        String[] arr = reader.readLine().split(" ");
        Deque<String> deque = new ArrayDeque<>(Arrays.asList(arr));
        partOne(deque);
        reader.close();
    }

    private static void partOne(Deque<String> q) {

        Map<String, List<String>> cache = new HashMap<>();

        for (int i = 0; i < 50; i++) {
            Deque<String> newQ = new LinkedList<>();
            List<String> sorted = q.stream().sorted().toList();
            Map<String, Long> cnt = new HashMap<>();
            sorted.forEach(n -> {
                cnt.put(n, cnt.getOrDefault(n, 0L) + 1);
            });
            Set<String> unique = new HashSet<>(sorted);
            q = new ArrayDeque<>(unique);
            while (!q.isEmpty()) {
                String old = q.pollFirst();
                StringBuilder newNums = new StringBuilder();
                if (cache.containsKey(old)) {
                    List<String> strings = cache.get(old);
                    Long count = cnt.getOrDefault(old, 1L);
                    for (int j = 0; j < count; j++) {
                        newQ.addAll(strings);
                    }
                    continue;
                }
                else if (old.equals("0")) {
                    newNums.append("1");
                } else if (old.length() % 2 == 0) {
                    int mid = old.length() / 2;
                    String firstPart = old.substring(0, mid);
                    String secondPart = old.substring(mid);

                    int indexF = 0;
                    while (firstPart.charAt(indexF) == '0' && indexF < firstPart.length() - 1) {
                        indexF++;
                    }

                    int indexS = 0;
                    while (secondPart.charAt(indexS) == '0' && indexS < secondPart.length() - 1) {
                        indexS++;
                    }

                    firstPart = firstPart.substring(indexF);
                    secondPart = secondPart.substring(indexS);

                    newNums.append(firstPart).append(",");
                    newNums.append(secondPart);
                } else {
                    String newNum = String.valueOf(Long.parseLong(old) * 2024);
                    newNums.append(newNum);
                }

                List<String> nums = Arrays.asList(newNums.toString().split(","));
                cache.putIfAbsent(old, new ArrayList<>());
                cache.get(old).addAll(nums);

                Long count = cnt.getOrDefault(old, 0L);
                for (int j = 0; j < count; j++) {
                    newQ.addAll(nums);
                }
            }
            q = newQ;
        }

        System.out.println(q.size());

    }
}
