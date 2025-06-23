import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


//BOJ 1717 집합의 표현
public class Main {
    static int[] pointer;
    static int[] priority;
    static final int UNION = 0;
    static final int COMPARE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        pointer = new int[n + 1];
        priority = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            pointer[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int type = temp[0];
            int lSet = find(temp[1]);
            int rSet = find(temp[2]);

            if (type == UNION) {
                if(lSet == rSet) continue;
                if (priority[lSet] > priority[rSet]) {
                    pointer[rSet] = lSet;
                } else {
                    pointer[lSet] = rSet;
                    if(priority[lSet] == priority[rSet])
                        priority[rSet]++;
                }
            }
            if (type == COMPARE) {
                if (lSet == rSet) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static int find(int x) {
        if (pointer[x] == x) {
            return x;
        } else {
            return find(pointer[x]);
        }
    }
}
//1 2
//0 0 1
//1 0 1
//
//