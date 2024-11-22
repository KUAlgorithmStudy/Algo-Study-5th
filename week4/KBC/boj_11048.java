import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int h = input[0];
        int w = input[1];
        int[][] map = new int[h + 1][w + 1];

        for (int i = 1; i <= h; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int k = 1; k <= w; k++) {
                map[i][k] = input[k - 1];
            }
        }

        int[][] dp = new int[h + 1][w + 1];
        dp[1][1] = map[1][1];
        for (int k = 2; k <= w; k++) { // 첫줄 초기화
            dp[1][k] = dp[1][k - 1] + map[1][k];
        }

        for (int i = 2; i <= h; i++) {
            for (int k = 1; k <= w; k++) {
                dp[i][k] = Math.max(dp[i][k - 1], dp[i - 1][k]) + map[i][k];
            }
        }
        System.out.println(dp[h][w]);
    }
}
