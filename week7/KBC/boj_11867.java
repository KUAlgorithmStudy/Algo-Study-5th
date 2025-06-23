import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ 11867 박스 나누기 게임
public class Main {
    static final boolean WIN = true;
    static final boolean LOSE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int max = Math.max(n, m);
        boolean[] dp = new boolean[max + 1];
        dp[1] = LOSE;
        dp[2] = WIN;

        for (int i = 3; i <= max; i++) {
            for (int k = 1; k <= i / 2; k++) {
                dp[i] = !(dp[k] | dp[i - k]);
                if(dp[i]) break;
            }
        }

        System.out.println(dp[n] | dp[m] ? "A" : "B");
    }
}