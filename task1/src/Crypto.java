import java.io.*;
import java.nio.Buffer;
import java.util.*;

class PC {
    int coins;
    int upPrice;

    public PC(int coins, int upPrice) {
        this.coins = coins;
        this.upPrice = upPrice;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getUpPrice() {
        return upPrice;
    }

    public void setUpPrice(int upPrice) {
        this.upPrice = upPrice;
    }
}
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
        List<PC> pcList;
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
                pcList = new ArrayList<>();
                for (int i = 0; i < nrPC; i++) {
                    String pair = br.readLine();
                    String[] val = pair.split(" ");
                    int coin = Integer.parseInt(val[0]);
                    int price = Integer.parseInt(val[1]);

                    pcList.add(new PC(coin, price));
                    coins[i] = coin;
                    upgradePrice[i] = price;
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
        private int getLastMin() {
            int min = coins[0];
            int index = -1;
            for (int i = 1; i < nrPC; i++) {
                if (min >= coins[i]) {
                    min = coins[i];
                    index = i;
                }
            }
            return min;
        }
        private int getPrices(){
            int sum = 0;
            for(int i = 0; i < pcList.size();i++){
                sum += pcList.get(i).getUpPrice();
            }
            return  sum;
        }
        private int getResult() {
            /*TODO:nr max de monede minate/ora dupa ce isi cumpara
              procesoare cu banii pe care i are
             */
            Collections.sort(pcList, new Comparator<PC>() {
                @Override
                public int compare(PC pc, PC t1) {
                    return t1.getCoins() - pc.getCoins();
                }
            });
            //caz de baza cand toate sunt egale
            // dar nu se pot upgrada toate
            if(pcList.get(0).getCoins() == pcList.get(nrPC).getCoins()){
                if (fiat < getPrices()){
                    return pcList.get(0).getCoins();
                }
            }
            int minCoins = pcList.get(0).getCoins();
            while (fiat > 0) {
                for(int i = 1; i < nrPC; i++){
                    if(pcList.get(i).getCoins() == minCoins){
                        fiat -= pcList.get(i).getUpPrice();
                        pcList.get(i).setCoins(minCoins++);
                    }
                }
            }
            return  pcList.get(0).getCoins();
        }

    }

    public static void main(String[] args) {
        new Task().solve();
    }
}