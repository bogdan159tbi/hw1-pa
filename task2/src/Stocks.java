import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Stocks {
    static class Task {
        public final static String INPUT_FILE = "stocks.in";
        public final static String OUTPUT_FILE = "stocks.out";

        int stocks, fiat, maxLoss;
        int[] price;
        int[] minPrice;
        int[] maxPrice;

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
            int[][] dp = new int[stocks + 1][fiat + 1];
            for (int i = 0; i <= fiat; i++) {
                dp[0][i] = 0;
            }
            //conditii
            // buget > 0
            //loss < maxLoss
            int currLoss = 0;
            for (int i = 1; i <= stocks; i++) {
                for (int cap = 0; cap <= fiat; cap++) {
                    dp[i][cap] = dp[i - 1][cap];
                    if (cap - price[i - 1] >= 0) {
                        int aux = dp[i - 1][cap - price[i - 1]] + maxPrice[i - 1] - price[i - 1];
                        if (currLoss + price[i - 1] - minPrice[i - 1] <= maxLoss) {
                            dp[i][cap] = Math.max(dp[i][cap], aux);
                        }
                    }
                }
            }
            return dp[stocks][fiat];
        }


        public static void main(String[] args) {
            new Task().solve();
        }
    }
}