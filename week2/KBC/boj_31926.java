import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//boj 31926 밤양갱
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = getNumber(n);
        System.out.println(8+count+1);

    }
    public static int getNumber(int n) {
        int number = 1;
        int count = 0;
        while(number < n+1) {
            number *= 2;
            count++;
        }
        return count;
    }
}
