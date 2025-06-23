import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//13458 시험 감독
public class Main {
    static int N, B, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt += 1;
            if (arr[i] <= B) {
                continue;
            }
            double viewers = (double) (arr[i] - B) / C;
            if ((viewers * 10) % 10 > 0)//올림을 위해 소수점 검사
                cnt += 1;
            cnt += (arr[i] - B) / C;
        }
        System.out.println(cnt);
    }
}
