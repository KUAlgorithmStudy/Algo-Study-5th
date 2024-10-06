import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//boj 4969 [월요일-토요일]
public class Main {
    static final int MAX = 300_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<Integer> msPrimes = getMsPrime();

        int n;
        while ((n = Integer.parseInt(br.readLine())) != 1) {
            sb.append(n).append(": ");
            for (int i = 0; i < msPrimes.size(); i++) {
                int msPrime = msPrimes.get(i);
                if (n % msPrime == 0) {
                    sb.append(msPrime).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static List<Integer> getMsPrime() {
        boolean[] isPrime = new boolean[MAX + 10];
        for (int i = 0; i < MAX; i += 7) {
            isPrime[i + 1] = true;
            isPrime[i + 6] = true;
        }
        isPrime[1] = false;

        List<Integer> msPrimes = new ArrayList<>();
        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) {
                msPrimes.add(i);
                for (int k = i * 2; k <= MAX; k += i) {
                    isPrime[k] = false;
                }
            }
        }
        return msPrimes;
    }
}
