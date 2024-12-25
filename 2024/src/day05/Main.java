package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        partOne();
    }


    private static void partOne() throws IOException {
        List<List<Integer>> validUpdates = new ArrayList<>();
        List<List<Integer>> wrongUpdates = new ArrayList<>();
        Map<Integer, Set<Integer>> comeBefore = new HashMap<>();
        Map<Integer, Set<Integer>> comeAfter = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader("src/day05/input"));

        boolean isRule = true;
        String line;

        while ( (line = reader.readLine()) != null) {

            if (line.isEmpty()) {
                isRule = false;
                continue;
            }

            if (isRule) {
                List<Integer> rules = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toList();
                int first = rules.get(0);
                int second = rules.get(1);

                comeBefore.putIfAbsent(first, new HashSet<>());
                comeBefore.get(first).add(second);

                comeAfter.putIfAbsent(second, new HashSet<>());
                comeAfter.get(second).add(first);
            }else {
                List<Integer> update = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();

                boolean validUpdate = isValidUpdate(comeBefore, comeAfter, update);

                if (validUpdate) {
                    validUpdates.add(update);
                }else {
                    wrongUpdates.add(update);
                }

            }
        }

        // partOne result
        // System.out.println(sumOfMiddleElements(validUpdates));
        List<List<Integer>> fixedUpdates = fixWrongUpdate(wrongUpdates, comeAfter, comeBefore);
        System.out.println(fixedUpdates.size());
        System.out.println(sumOfMiddleElements(fixedUpdates));

    }

    private static List<List<Integer>> fixWrongUpdate(List<List<Integer>> wrongUpdates, Map<Integer, Set<Integer>> comesAfter, Map<Integer, Set<Integer>> comesBefore) {
        List<List<Integer>> validUpdates = new ArrayList<>();
        for (List<Integer> wrongUpdate : wrongUpdates) {


            ArrayList<Integer> mutable = new ArrayList<>(wrongUpdate);
            mutable.sort((a, b) -> {
                if (comesAfter.getOrDefault(a, new HashSet<>()).contains(b)) {
                    return -1;
                } else if (comesAfter.getOrDefault(b, new HashSet<>()).contains(a)) {
                    return 1;
                }else{
                    return 0;
                }
            });

            validUpdates.add(mutable);

        }

        return validUpdates;

    }


    private static long sumOfMiddleElements(List<List<Integer>> updates) {
        System.out.println(updates);
         return updates.stream()
                .mapToInt(n -> n.get((n.size() / 2)))
                 .sum();
    }

    private static boolean isValidUpdate(Map<Integer, Set<Integer>> comeBefore, Map<Integer, Set<Integer>> comeAfter, List<Integer> update) {
        int index = 0;

        while (index < update.size()) {

            int currVal = update.get(index);
            var left = update.subList(0, index);
            var right = update.subList(index + 1, update.size());


            if (comeBefore.containsKey(currVal)) {

                Set<Integer> comesBefore = comeBefore.get(currVal);

                if (left.stream().anyMatch(comesBefore::contains)) {
                    return false;
                }

            }

            if (comeAfter.containsKey(currVal)) {

                Set<Integer> comesAfter = comeAfter.get(currVal);

                if (right.stream().anyMatch(comesAfter::contains)) {
                    return false;
                }
            }

            index++;

        }
        return true;
    }


}
/*

* 11 | 15
* 11 | 19
* 4 | 11
* 5 | 11
*
* 16 12 4 12 5 6 91
* */
