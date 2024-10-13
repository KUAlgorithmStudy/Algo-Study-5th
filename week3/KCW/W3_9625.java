import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//9625 BABBA
public class Main {
    static int K, A, B;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        A = 1;
        B = 0;
        int tmpA, tmpB;
        for(int i = 0; i<K; i++){
            tmpA = B;
            tmpB = A+B;
            A = tmpA;
            B = tmpB;
        }
        System.out.println(A+" "+B);
    }
}
