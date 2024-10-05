import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//BOJ 27535 [제주 초콜릿 지키기]
public class Main {
    public static class Choco {
        int amount;
        String taste;

        public Choco() {
            amount = 0;
            taste = "";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tastes = {"H", "T", "C", "K", "G"};
        Choco[] choco = new Choco[5];
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < 5; i++) {
            choco[i] = new Choco();
            choco[i].amount = input[i];
            choco[i].taste = tastes[i];
        }

        int n = Integer.parseInt(br.readLine());
        int ones = sumAmountChoco(choco) % 10;
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int k = 0; k < 5; k++) {
                choco[k].amount -= input[k];
            }
            int newAmount = sumAmountChoco(choco);

            if (ones == 1 || ones == 0) {
                ones = 10;
            }
            sb.append(Integer.toString(newAmount, ones)).append("7H\n");
            printChoco(choco.clone(), sb);

            ones = newAmount % 10;
        }
        System.out.println(sb);
    }

    public static int sumAmountChoco(Choco[] choco) {
        int sum = 0;
        for (Choco c : choco) {
            sum += c.amount;
        }
        return sum;
    }

    public static void printChoco(Choco[] choco, StringBuilder sb) {
        Arrays.sort(choco, (x, y) -> {
            if (x.amount == y.amount) {
                return x.taste.charAt(0) - y.taste.charAt(0);
            }
            return y.amount - x.amount;
        });

        if (choco[0].amount == 0) {
            sb.append("NULL\n");
        } else {
            for (Choco c : choco) {
                if (c.amount != 0) {
                    sb.append(c.taste);
                }
            }
            sb.append("\n");
        }
    }
}
