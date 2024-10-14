import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//boj 1166 선물
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long N = input[0];
        long L = input[1];
        long W = input[2];
        long H = input[3];

        double left = 0;
        double right = Math.min(L, Math.min(W, H));
        for (int i = 0; i < 10000; i++) {
            double mid = (left + right) / 2;
            long amount = (long) (L / mid) * (long) (W / mid) * (long) (H / mid);
            if (N <= amount) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(right);
    }
}
