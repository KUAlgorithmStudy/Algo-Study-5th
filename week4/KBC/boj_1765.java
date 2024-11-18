import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Integer[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        root = new Integer[n+1];
        for(int i=0; i<=n; i++) {
            root[i] = i;
        }

        List<List<Integer>> enermys = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            enermys.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            String[] input = br.readLine().split(" ");
            String type = input[0];
            int n1 = Integer.parseInt(input[1]);
            int n2 = Integer.parseInt(input[2]);

            if(type.equals("F")) {
                union(n1, n2);
            }
            else if(type.equals("E")) {
                enermys.get(n1).add(n2);
                enermys.get(n2).add(n1);
                for(int friend : enermys.get(n2)) {
                    union(n1, friend);
                }
                for(int friend : enermys.get(n1)) {
                    union(n2, friend);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=1; i<=n; i++) {
            set.add(find(i));
        }
        System.out.println(set.size());
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        root[y] = x;
    }
    public static int find(int x) {
        if(root[x] == x) {
            return x;
        }
        return find(root[x]);
    }
}