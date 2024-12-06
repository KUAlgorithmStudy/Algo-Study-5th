import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1915 가장 큰 정사각형
/*
가장 큰 거 구해라 -> DP 아님 그리디임
 */
public class Main {
    static int n, m, max;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = Character.getNumericValue(line[j]);
                dp[i][j] = map[i][j];
                if (i >= 1 && j >= 1) {
                    if (dp[i - 1][j] > 0 && dp[i - 1][j - 1] > 0 && dp[i][j - 1] > 0 && dp[i][j] > 0) {
                        int min = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                        dp[i][j] = min + 1;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max * max);
    }
}
