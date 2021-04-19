import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Valley {
    static class Task {
        public final static String INPUT_FILE = "valley.in";
        public final static String OUTPUT_FILE = "valley.out";

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
            return 0;
        }


        public static void main(String[] args) {
            new Task().solve();
        }
    }
}