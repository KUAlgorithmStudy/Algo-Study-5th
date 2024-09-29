import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M, N, solution;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //계산
        int total = 0;
        for (int i = 0; i < N; i++) {
            total += arr[i];
        }
        for (int blueray_size = total / M; blueray_size <= total; blueray_size++) {
            int sum = 0;
            int idx = 0;
            for (int j = 0; j < M; j++) {
                sum = 0;
                for (; idx < N && blueray_size >= sum; idx++) {
                    sum += arr[idx];
                }
                idx--;
            }
            if (blueray_size >= sum) {
                solution = blueray_size;
                break;
            }
        }
        System.out.println(solution);
    }
}