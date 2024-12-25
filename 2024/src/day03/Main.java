package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

    }

    public static void partOne() throws IOException {
        // Your regex pattern
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

        BufferedReader reader = new BufferedReader(new FileReader("src/day03/input.txt"));

        String text;
        long res = 0;
        while ( (text = reader.readLine()) != null) {
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                System.out.println(matcher.group());  // This will print the whole matched substring like 'mul(594,203)'
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                res += ((long) x * y);
            }
        }

        System.out.println(res);
        reader.close();
    }

    private static void partTwo() throws IOException {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
        BufferedReader reader = new BufferedReader(new FileReader("src/day03/input.txt"));

        boolean mulEnabled = true;  // Initially, mul instructions are enabled.
        long sum = 0;
        String text;

        while ( (text = reader.readLine() ) != null)  {
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                if (matcher.group().startsWith("mul")) {
                    if (mulEnabled) {
                        int num1 = Integer.parseInt(matcher.group(1));
                        int num2 = Integer.parseInt(matcher.group(2));
                        sum += ((long) num1 * num2);
                        System.out.println("Enabled mul: " + num1 + "*" + num2 + "=" + (num1*num2));
                    } else {
                        System.out.println("Disabled mul, skipped: " + matcher.group());
                    }
                } else if (matcher.group().equals("do()")) {
                    mulEnabled = true;
                    System.out.println("Enabling mul instructions.");
                } else if (matcher.group().equals("don't()")) {
                    mulEnabled = false;
                    System.out.println("Disabling mul instructions.");
                }
            }

        }
        System.out.println("Total sum: " + sum);
    }
}
