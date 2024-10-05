import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//BOJ 2343 기타레슨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] set = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = set[0];
        int m = set[1];
        int[] lengths = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //전체 길이를 통해 영상 당 최소길이 구하기
        int max = 0;
        for (int i = 0; i < n; i++) {
            max += lengths[i];
        }
        int min = (max + m - 1) / m;
        for (int sumLength = min; sumLength <= max; sumLength++) {
            int k = 0;
            for (int j = 0; j < m; j++) {
                int sum = 0;
                while (k < n && sum + lengths[k] <= sumLength) {
                    sum += lengths[k];
                    k++;
                }
            }

            if (k == n) {
                System.out.println(sumLength);
                break;
            }
        }
    }
}
