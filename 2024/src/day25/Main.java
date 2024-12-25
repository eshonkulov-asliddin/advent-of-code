package day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day25/input.txt"));

        List<List<String>> locks = new ArrayList<>();
        List<List<String>> keys = new ArrayList<>();

        List<String> rows = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {

            if (!line.isEmpty()) {
                rows.add(line);
            }

            if (line.isEmpty()) {
                if (rows.get(0).equals("#####")) {
                    locks.add(new ArrayList<>(rows));
                }else {
                    keys.add(new ArrayList<>(rows));
                }
                rows = new ArrayList<>();
            }
        }

        if (rows.get(0).equals("#####")) {
            locks.add(new ArrayList<>(rows));
        }else {
            keys.add(new ArrayList<>(rows));
        }

//        System.out.println(locks);
//        System.out.println(keys);

        partOne(locks, keys);
        reader.close();

    }

    private static void partOne(List<List<String>> locks, List<List<String>> keys) {
        int res = 0;
        for (List<String> lock : locks) {
            for (List<String> key : keys) {
                if (!doOverlapColumn(lock, key)) {
                    res += 1;
                }
            }
        }

        System.out.println(res);

    }

    public static boolean doOverlapColumn(List<String> locks, List<String> keys) {

        for (int i = 0; i < 5; i++) {
            int total = countHash(locks, i) + countHash(keys, i);

            if (total > 7) {
                return true;
            }

        }

        return false;
    }

    private static int countHash(List<String> rows, int row) {
        int cnt = 0;
        for (String curr : rows) {
            cnt += curr.charAt(row) == '#' ? 1 : 0;
        }
        return cnt;
    }
}
