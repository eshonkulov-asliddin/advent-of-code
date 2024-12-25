package day01;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/day01/input.txt"));

        List<Integer> s1 = new ArrayList<>();
        List<Integer> s2 = new ArrayList<>();
        Map<Integer, Integer> rightFreq = new HashMap<>();

        String line;
        while ( (line = reader.readLine() ) != null ) {
            String[] split = line.split("   ");
            Integer left = Integer.valueOf(split[0].strip());
            Integer right = Integer.valueOf(split[1].strip());
            s1.add(left);
            s2.add(right);
            rightFreq.putIfAbsent(right, 0);
            rightFreq.put(right, rightFreq.get(right) + 1);
        }

        // task1
        /*
        s1.sort(null);
        s2.sort(null);

        Iterator<Integer> itr1 = s1.iterator();
        Iterator<Integer> itr2 = s2.iterator();

        System.out.println(s1);
        System.out.println(s2);
        long TOTAL = 0;
        while ( itr1.hasNext() && itr2.hasNext() ) {
            TOTAL += Math.abs(itr1.next() - itr2.next());
        }

        System.out.println(TOTAL);
        */

        // task2
        long TOTAL = 0;
        System.out.println(rightFreq);
        for (var num : s1) {
            Integer cnt = rightFreq.getOrDefault(num, 0);
            TOTAL += ((long) num * cnt);
        }
        System.out.println(TOTAL);
    }
}
