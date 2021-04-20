import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Stocks {

    static class Task {
        public final static String INPUT_FILE = "/home/bogdan/Desktop/pa/hw-gigel/public_tests/stocks/input/12-stocks.in";
        public final static String OUTPUT_FILE = "stocks.out";

        int stocks, fiat, maxLoss;
        int[] price;
        int[] minPrice;
        int[] maxPrice;
        int[][][] dp;
        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                FileInputStream file = new FileInputStream(INPUT_FILE);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(file)
                );
                StringTokenizer st = new StringTokenizer(br.readLine());
                stocks = Integer.parseInt(st.nextToken());
                fiat = Integer.parseInt(st.nextToken());
                maxLoss = Integer.parseInt(st.nextToken());
                price = new int[stocks];
                minPrice = new int[stocks];
                maxPrice = new int[stocks];
                int[][][] dp = new int[stocks + 1][fiat + 1][maxLoss+1];
                for (int i = 0; i < stocks; i++) {
                    String pair = br.readLine();
                    String[] val = pair.split(" ");
                    price[i] = Integer.parseInt(val[0]);
                    minPrice[i] = Integer.parseInt(val[1]);
                    maxPrice[i] = Integer.parseInt(val[2]);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        private int getResult() {
            //TODO: find maximum profit
            //TODO: check if currentLoss <= maxLoss
            //echivalent  risk reward
            //problema rucsac dar fara fractiuni
            //max profit while currentLoss < maxLoss
            //capacitatea rucsac = maxLoss

            Arrays.fill(dp[0],0);
            //conditii
            // buget > 0
            //loss < maxLoss
            for (int i = 1; i <= stocks; i++) {
                for (int cap = 0; cap <= fiat; cap++) {
                    for(int loss = 0; loss <= maxLoss; loss++) {
                        dp[i][cap][loss] = dp[i - 1][cap][loss];
                        if (loss >=  minPrice[i-1] && cap >= price[i - 1]) {
                            int aux = dp[i - 1][cap - price[i - 1]][loss - minPrice[i-1]] + maxPrice[i - 1] - price[i - 1];
                            dp[i][cap][loss] = Math.max(dp[i][cap][loss], aux);
                        }
                    }
                }
            }
            return dp[stocks][fiat][maxLoss];
        }


        public static void main(String[] args) {
            new Task().solve();
        }
    }
}