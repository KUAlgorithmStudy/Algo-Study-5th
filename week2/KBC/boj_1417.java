import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int MY_INDEX = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nVote = new int[n];
        for (int i = 0; i < n; i++) {
            nVote[i] = Integer.parseInt(br.readLine());
        }

        if(n == 1) {
            System.out.println(0);
            return;
        }

        int count = 0;
        int maxIndex= findMaxIndex(nVote);
        while(nVote[maxIndex] >= nVote[MY_INDEX]) {
            nVote[MY_INDEX] += 1;
            nVote[maxIndex] -= 1;
            count += 1;
            maxIndex= findMaxIndex(nVote);
        }

        System.out.println(count);
    }
    public static int findMaxIndex(int[] nVote) {
        int maxIndex = 1;
        for(int i=2; i<nVote.length; i++) {
            if(nVote[maxIndex] < nVote[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
