package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Pattern XPatern = Pattern.compile("X\\+([0-9]+)");
    static Pattern YPattern = Pattern.compile("Y\\+([0-9]+)");
    static Pattern TXPattern = Pattern.compile("X\\=([0-9]+)");
    static Pattern TYPattern = Pattern.compile("Y\\=([0-9]+)");

    public static void main(String[] args) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader("src/day13/input.txt"));

        long totalCost = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String btnA = line;
            String btnB = reader.readLine();
            String prize = reader.readLine();
            reader.readLine();
//            totalCost += partOne(btnA, btnB, prize);
            totalCost += partTwo(btnA, btnB, prize);
        }
        System.out.println(totalCost);
        reader.close();
    }

    private static long partTwo(String btnA, String btnB, String prize) {

        long AX = get(btnA, XPatern);
        long AY = get(btnA, YPattern);

        long BX = get(btnB, XPatern);
        long BY = get(btnB, YPattern);

        long TX = get(prize, TXPattern);
        long TY = get(prize, TYPattern);

        TX += Long.parseLong("10000000000000");
        TY += Long.parseLong("10000000000000");

        // not my solution!!!
        long b = (TX*AY-TY*AX) / (AY*BX-BY*AX);
        long a = (TX*BY-TY*BX) / (BY*AX-BX*AY);
        if (AX * a + BX * b == TX && AY * a + BY * b == TY){
            return 3 * a + b;
        }
        return 0;
    }

    private static int partOne(String btnA, String btnB, String prize) {

        long AX = get(btnA, XPatern);
        long AY = get(btnA, YPattern);

        long BX = get(btnB, XPatern);
        long BY = get(btnB, YPattern);

        long TX = get(prize, TXPattern);
        long TY = get(prize, TYPattern);

        int cost = 0;
        int minCost = 0;
        for (int a = 1; a < 100; a++ ) {
            for (int b = 1; b < 100; b++) {
                if ((AX * a) + (BX * b) == TX && (AY * a) + (BY * b) == TY){
                    cost = (3 * a) + (b);
                    if (minCost == 0 || cost < minCost){
                        minCost = cost;
                    }
                }
            }
        }

        return minCost;

    }

    private static long get(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
