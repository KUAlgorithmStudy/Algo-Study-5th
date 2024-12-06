import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 1765. 닭싸움 팀 정하기
public class Main {
    static int n, m;
    static int[] arr;
    static ArrayList<Integer>[] enemies, friends;

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) arr[y] = x;
    }

    public static int find(int x) {
        if (arr[x] == x) return x;
        return arr[x] = find(arr[x]);
    }

    public static void main(String args[]) throws IOException {
        // 1, 2, 3, 4, 5, 6
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        enemies = new ArrayList[n + 1];
        friends = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
            enemies[i] = new ArrayList<>();
            friends[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String relationship = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (relationship.equals("F")) {
                union(p, q);
                friends[p].add(q);
                friends[q].add(p);
            } else if (relationship.equals("E")) {
                enemies[p].add(q);
                enemies[q].add(p);
            }
        }

        for (int i = 1; i < n; i++) {
            //친구의 친구는 친구
            for (int j = 0; j < friends[i].size() - 1; j++) {
                int p = friends[i].get(j);
                int q = friends[i].get(j + 1);
                union(p, q);
            }

            //적의 적은 친구
            for (int j = 0; j < enemies[i].size() - 1; j++) {
                int p = enemies[i].get(j);
                int q = enemies[i].get(j + 1);
                union(p, q);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }
}
