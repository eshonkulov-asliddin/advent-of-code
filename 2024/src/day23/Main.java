package day23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day23/input.txt"));

        Map<String, List<String>> freq = new HashMap<>();
        reader.lines()
                .forEach(line -> {
                    String[] arr = line.split("-");
                    freq.putIfAbsent(arr[0], new ArrayList<>());
                    freq.putIfAbsent(arr[1], new ArrayList<>());

                    freq.get(arr[0]).add(arr[1]);
                    freq.get(arr[1]).add(arr[0]);
                });

//        partOne(freq);
        partTwo(freq);
        reader.close();
    }

    public static void partTwo(Map<String, List<String>> freq) {


        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : freq.entrySet()) {

            String key = entry.getKey();
            List<String> value = entry.getValue();
            List<String> expected = new ArrayList<>(value);
            for (String curr : value) {
                List<String> list = expected.stream().filter(n -> !n.equals(curr)).toList();
                if (!checkMapContainsValueByKey(curr, list, freq)) {
                    expected.remove(curr);
                }
            }
            if (expected.size() > result.size()) {
                result = expected;
                result.add(key);
            }

        }

        result.sort(null);
        System.out.println(String.join(",", result));

    }

    public static void partOne(Map<String, List<String>> freq) {
        Set<String> res = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : freq.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            for (int i = 0; i < values.size() - 1; i++) {
                for (int j = i+1; j < values.size(); j++) {

                    String second = values.get(i);
                    String third = values.get(j);


                    boolean isValid = checkMapContainsValueByKey(second, List.of(key, third), freq) &&
                            checkMapContainsValueByKey(third, List.of(key, second), freq);

                    List<String> curr = new ArrayList<>(List.of(key, second, third));
                    if (isValid && hasComputerThatStartsWithT(curr)) {
                        curr.sort(null);
                        String val = String.join("-", curr);
                        res.add(val);
                    }

                }
            }
        }

        System.out.println(res.size());
        System.out.println(res);
    }
    public static boolean hasComputerThatStartsWithT(List<String> computers) {
        return computers.stream().anyMatch(comp -> comp.startsWith("t"));
    }

    public static boolean checkMapContainsValueByKey(String key, List<String> values, Map<String, List<String>> freq) {
        return new HashSet<>(freq.getOrDefault(key, new ArrayList<>())).containsAll(values);

    }
}
