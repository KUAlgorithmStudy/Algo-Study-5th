import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static double L, W, H;

    // 주어진 변 A의 정육면체가 박스 안에 N개 이상 들어가는지 확인하는 함수
    static boolean isContain(double A) {
        long h1 = (long) (H / A);  // 높이 방향에 몇 개 들어가는지
        long w1 = (long) (W / A);  // 너비 방향에 몇 개 들어가는지
        long l1 = (long) (L / A);  // 길이 방향에 몇 개 들어가는지
        return h1 * w1 * l1 >= N;  // 전체 개수 비교
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        N = Integer.parseInt(st.nextToken());
        L = Double.parseDouble(st.nextToken());
        W = Double.parseDouble(st.nextToken());
        H = Double.parseDouble(st.nextToken());

        // 이진 탐색 초기값 설정
        double low = 0;
        double high = Math.min(Math.min(L, W), H);
        double mid = 0;

        // 오차 범위를 줄여 더 정확하게 탐색
        double A = 0;
        while (high > low) {
            mid = (low + high) / 2;
            if (isContain(mid)) {
                if(low == mid) break;
                low = mid; // mid 크기의 상자를 더 크게 할 수 있으면 low 값을 증가
            } else {
                if(high == mid) break;
                high = mid; // mid 크기의 상자를 더 작게 해야 하면 high 값을 감소
            }
        }

        // 결과 출력: 필요한 자릿수에 맞춰 출력
        System.out.printf("%.15f\n", low);
    }
}
