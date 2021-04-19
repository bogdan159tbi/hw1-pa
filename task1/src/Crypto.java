import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Crypto {
    static class Task {
        //sa modific denumirea fisierelor in out pt ca
        //am pus ca sa iau doar pt 0-crypto.in din src
        public final static String INPUT_FILE = "/home/bogdan/Desktop/pa/hw-gigel/public_tests/crypto/input/10-crypto.in";
        public final static String OUTPUT_FILE = "/home/bogdan/Desktop/pa/hw-gigel/public_tests/crypto/out/10-crypto.out";

        //declara variabile necesare
        // n b + vectorii
        // b = nr de bani
        // n = nr calculatoare
        // pi = nr de monede minate pe ora de calculatorul i
        // ui = cost upgrade pc pentru a mina cu o moneda mai mult pe ora
        int nrPC, fiat;
        int[] coins; // n elemente
        int[] upgradePrice; // n elemente

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
                nrPC = Integer.parseInt(st.nextToken());
                fiat = Integer.parseInt(st.nextToken());
                coins = new int[nrPC];
                upgradePrice = new int[nrPC];
                for (int i = 0; i < nrPC; i++) {
                    String pair = br.readLine();
                    String[] val = pair.split(" ");
                    coins[i] = Integer.parseInt(val[0]);
                    upgradePrice[i] = Integer.parseInt(val[1]);
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

        private int getMin() {
            int min = coins[0];
            for (int i = 1; i < nrPC; i++) {
                if (min > coins[i])
                    min = coins[i];
            }
            return min;
        }
        private int getResult() {
            /*TODO:nr max de monede minate/ora dupa ce isi cumpara
              procesoare cu banii pe care i are
             */
            int minCoins = 0;
            while (fiat > 0) {
                minCoins = getMin();
                for (int i = 0; i < nrPC; i++) {
                    if (coins[i] == minCoins && fiat > 0) {
                        fiat -= upgradePrice[i];
                        coins[i]++;
                    }
                }
            }
            minCoins = getMin();
            return minCoins;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}