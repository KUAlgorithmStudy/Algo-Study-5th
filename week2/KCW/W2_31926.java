import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 31926 밤양갱
public class Main {
    static int N, minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //밤양갱 최소 시간 계산
        N++; // daldidalgo*n + daldida까지 포함
        minTime = 9; // i = 0, i = 1 미리 계산
        for (int i = 2; i < N;) {
            int combine = 2;
            while (i + combine < N && combine < i) {
                combine++;
            }
            if (combine + i <= N) i += combine;
            else i++;
            minTime++;
        }
        minTime++;//끝에 n 넣기
        System.out.println(minTime);
    }
}