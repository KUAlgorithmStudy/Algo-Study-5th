import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[] isPrime = getPrimeNumbers();

        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int k = i; k < n; k++) {
                int length = k - i + 1;
                sum += arr[k];
                //판별
                if (isPrime[length] && isPrime[sum]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static boolean[] getPrimeNumbers() {
        int max = 1000000;
        boolean[] isPrime = new boolean[max+1];
        for (int i = 2; i < max + 1; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                for (int k = 2; k * i <= max; k++) {
                    isPrime[k * i] = false;
                }
            }
        }
        return isPrime;
    }
}
