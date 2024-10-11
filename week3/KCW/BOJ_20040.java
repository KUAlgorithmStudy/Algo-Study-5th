import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int n, m;

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootB != rootA){
            parent[rootB] = rootA;
        }
    }

    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());// 점 개수
        m = Integer.parseInt(st.nextToken());// 차례의 수
        parent = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());//입력

            if(find(x) == find(y)){
                System.out.println(i+1);
                return;
            }

            union(x, y);
        }
        System.out.println(0);
    }
}