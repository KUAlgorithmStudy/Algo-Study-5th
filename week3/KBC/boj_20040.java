import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int p1 = Integer.parseInt(input[0]);
            int p2 = Integer.parseInt(input[1]);

            if (find(p1) == find(p2)) {
                System.out.println(i + 1);
                return;
            }
            combineGroup(p1, p2);
        }
        System.out.println(0);
    }

    public static int find(int p1) {
        if (parent[p1] == p1) return p1;
        else return find(parent[p1]);
    }

    private static void combineGroup(int p1, int p2) {
        int origin1 = find(p1);
        int origin2 = find(p2);
        if (origin1 != origin2) {
            parent[origin2] = origin1;
        }
    }
}