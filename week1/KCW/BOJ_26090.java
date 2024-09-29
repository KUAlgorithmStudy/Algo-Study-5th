import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//26090 완전한 수열
public class Main {
    static int N, maxValue = 1000000;
    static int[] a;
    static boolean[] isPrime = new boolean[maxValue + 1];

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        eratostenes();

        //계산
        int safety = calSafety();
        System.out.println(safety);

    }

    static void eratostenes() {
        for (int i = 2; i <= maxValue; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= maxValue; i++) {
            if (!isPrime[i]) continue;
            for (int j = 2; j * i <= maxValue; j++)
                isPrime[i * j] = false;
        }
    }

    static int calSafety() {
        int safety = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                //sum을 구하고, 소수인지 확인
                int sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += a[j];
                }
                if (isPrime[sum]) safety++;
                for (int j = 1; j <= N - i; j++) {
                    sum -= a[j - 1];
                    sum += a[j + i - 1];
                    if (isPrime[sum]) safety++;
                }
            }
        }
        return safety;
    }
}