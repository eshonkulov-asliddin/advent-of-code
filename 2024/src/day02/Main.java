package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static boolean FALLBACK = true;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/day02/test.txt"));

        String line;
        int res = 0;
        while ((line = reader.readLine()) != null) {
            FALLBACK = true;
            int[] nums = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int first = nums[0];
            int second = nums[1];

            if (first == second || Math.abs(first - second) > 3) {
                if (FALLBACK) {
                    FALLBACK = false;
                    int[] firstCase = Arrays.stream(nums).filter(n -> n != first).toArray();
                    int[] secondCase = Arrays.stream(nums).filter(n -> n != second).toArray();

                    boolean resp = solve(firstCase) || solve(secondCase);
                    res += resp ? 1 : 0;
                }
            }else if (first < second) {
                res += incOrd(nums) ? 1 : 0;
            }else {
                res += decOrd(nums) ? 1 : 0;
            }
        }

        System.out.println(res);
    }

    public static boolean incOrd(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int prev = nums[i-1];

            if (prev >= curr || curr-prev > 3) {
                if (FALLBACK) {
                    FALLBACK = false;
                    int[] firstCase = Arrays.stream(nums).filter(n -> n != curr).toArray();
                    int[] secondCase = Arrays.stream(nums).filter(n -> n != prev).toArray();

                    return solve(firstCase) || solve(secondCase);
                }else{
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean decOrd(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int prev = nums[i-1];

            if (curr >= prev || prev-curr > 3) {
                if (FALLBACK) {
                    FALLBACK = false;
                    int[] firstCase = Arrays.stream(nums).filter(n -> n != curr).toArray();
                    int[] secondCase = Arrays.stream(nums).filter(n -> n != prev).toArray();

                    return solve(firstCase) || solve(secondCase);
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isIncOrd(int[] nums) {
        int first = nums[0];
        int second = nums[1];

        return first < second;
    }

    public static boolean solve(int[] nums) {
        boolean incOrd = isIncOrd(nums);

        if (incOrd) {
            return incOrd(nums);
        }else {
            return decOrd(nums);
        }
    }

}
