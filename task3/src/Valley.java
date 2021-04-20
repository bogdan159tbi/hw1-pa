import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Valley {
    static class Task {
        public final static String INPUT_FILE = "/home/bogdan/Desktop/pa/hw-gigel/public_tests/valley/input/5-valley.in";
        public final static String OUTPUT_FILE = "valley.out";

        int nr;
        int [] heights;

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
                nr = Integer.parseInt(st.nextToken());
                heights = new int[nr];
                String pair = br.readLine();
                String[] val = pair.split(" ");
                for (int i = 0 ; i < nr ;i++) {
                    heights[i] = Integer.parseInt(val[i]);
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
            int minValue = heights[0];
            for(int i = 1 ; i < nr; i++){
                if(minValue > heights[i]){
                    minValue = heights[i];
                }
            }
            int index = 1, hours = 0;
            for(int i = 0 ; i <= nr -1 ;i++){
                if(minValue >=  heights[i]){
                    minValue = heights[i];
                    index = i;
                }
            }
            //cum aveam inainte
            //daca index == 1 si tre sa iau prefix sau sufix doar 2
            //si calc min intre indicii 1 si nr -1
            //modificam de la stanga la dreapta pt sufix

            //daca avem sir crescator / descrescator
            if(index == 0){
                index += 1;
            }
            for(int i = 1 ;i <= index;i++){
                if(heights[i] > heights[i-1]) {
                    hours += heights[i] - heights[i-1];
                    heights[i] = heights[i-1];
                }
            }
            if(index == nr -1){
                index -= 1;
            }
            for(int i = nr - 2 ; i >= index  ; i--){
                if(heights[i] > heights[i+1] ){
                    hours += heights[i-1] - heights[i];
                    heights[i] = heights[i + 1];
                }
            }
            return hours;
        }


        public static void main(String[] args) {
            new Task().solve();
        }
    }
}