import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int max;
    static int n;
    static boolean[] isBroken;
    static int[][] eggs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        max = 0;
        eggs = new int[n][2];
        isBroken = new boolean[n];
        for(int i=0; i<n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            eggs[i][0] = input[0];
            eggs[i][1] = input[1];
        }
        dfs(0,0);
        System.out.println(max);
    }
    public static void dfs(int index, int count) {
        max = Math.max(count, max);
        if(index >= n) {
            return;
        }
        if(eggs[index][0] <= 0) {
            dfs(index+1, count);
            return;
        }

        for(int i=0; i<n; i++) {
            if(i == index) continue;
            if(eggs[index][0] <= 0 || eggs[i][0] <= 0) continue; //이미 깨졌으면
            attack(index, i);
            dfs(index+1, count + countBrokenEgg(index ,i));
            restore(index, i);
        }
    }
    private static int countBrokenEgg(int e1, int e2) {
        int count = 0;
        if(eggs[e1][0] <= 0) count++;
        if(eggs[e2][0] <= 0) count++;
        return count;
    }
    private static void attack(int e1, int e2) {
        eggs[e1][0] -= eggs[e2][1];
        eggs[e2][0] -= eggs[e1][1];
    }
    private static void restore(int e1, int e2) {
        eggs[e1][0] += eggs[e2][1];
        eggs[e2][0] += eggs[e1][1];
    }
}
