package day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/day19/input.txt"));

        List<String> towels = new ArrayList<>();
        List<String> designs = new ArrayList<>();
        boolean isTowel = true;

        // Reading the input and separating towel patterns and design patterns
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                isTowel = false; // Switch to reading designs after the blank line
            } else if (isTowel) {
                towels.addAll(Arrays.stream(line.split(", ")).toList()); // Adding towel patterns
            } else {
                designs.add(line); // Adding design patterns
            }
        }

        reader.close();

        // Use a set to store all towel patterns for quick lookup
        int validDesignCount = 0;
        int totalPossibleCnt = 0;

        towels.sort(Comparator.comparing(String::length));
        // Check each design
        for (String design : designs) {
            // Use memoization to avoid recomputation
            Map<String, Boolean> memo = new HashMap<>();
            List<String> prefix = new ArrayList<>();
            if (partOne(design, towels, memo, prefix)) {
                validDesignCount++;
                List<String> possibleCases = new ArrayList<>();
                partTwo(prefix, towels, "", possibleCases);
                totalPossibleCnt += possibleCases.size();
                System.out.println(prefix);
                System.out.println(possibleCases);
            }
        }

        System.out.println(validDesignCount);
        System.out.println(validDesignCount + totalPossibleCnt);
    }

    private static void partTwo(List<String> prefixes, List<String> towels, String curr, List<String> cases) {
        if (towels.contains(curr)) {
            cases.add(curr);
            return;
        }

        if (prefixes.isEmpty()) {
            return;
        }


        for (String prefix : prefixes) {
            curr += prefix;
            partTwo(prefixes.subList(1, prefixes.size()), towels, curr, cases);
        }

    }
    private static boolean partOne(String design, List<String> towelSet, Map<String, Boolean> memo, List<String> appliedPrefixes) {
        if (design.isEmpty()) {
            return true;
        }

        if (memo.containsKey(design)) {
            return memo.get(design);
        }

        for (String towel : towelSet) {
            if (design.startsWith(towel)) {
                appliedPrefixes.add(towel);
                String remainingDesign = design.substring(towel.length());
                if (partOne(remainingDesign, towelSet, memo, appliedPrefixes)) {
                    memo.put(design, true);
                    return true;
                }
            }
        }

        memo.put(design, false);
        return false;
    }
}
