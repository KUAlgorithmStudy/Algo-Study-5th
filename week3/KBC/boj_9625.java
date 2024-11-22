import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int nA = 1;
        int nB = 0;
        for(int i=1; i<=n; i++) {
            int temp = nB;
            nB = nB + nA;
            nA = temp;
        }
        System.out.println(nA + " " + nB);
    }
}
