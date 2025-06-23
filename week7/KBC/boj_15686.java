import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//BOJ 15686 [치킨 배달]
public class Main {
    static int N;
    static int M;
    static List<Pos> houses;
    static List<Pos> stores;
    static int numberOfStore;
    static boolean[] isVisited;
    static int[] selectedStore;
    static int result;


    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(Pos p) {
            return Math.abs(x - p.x) + Math.abs(y - p.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initialize(br);
        dfs(0, 0);
        System.out.println(result);
    }

    public static void initialize(BufferedReader br) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        houses = new ArrayList<>();
        stores = new ArrayList<>();

        for (int y = 1; y <= N; y++) {
            String[] line = br.readLine().split(" ");
            for (int x = 1; x <= N; x++) {
                if (line[x - 1].equals("1")) {
                    houses.add(new Pos(x, y));
                }
                if (line[x - 1].equals("2")) {
                    stores.add(new Pos(x, y));
                }
            }
        }

        numberOfStore = stores.size();
        isVisited = new boolean[numberOfStore];
        selectedStore = new int[M];
        result = Integer.MAX_VALUE;
    }

    public static void dfs(int depth, int init) {
        if (depth == M) {
            //도시의 치킨 거리 구하기
            result = Math.min(result, getTotalDistance());
            return;
        }
        //치킨집 M개 선택
        for (int i = init; i < stores.size(); i++) {
            selectedStore[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    public static int getTotalDistance() {
        int totalDistance = 0;
        for (Pos house : houses) {
            int distance = Integer.MAX_VALUE;
            for (int depth = 0; depth < M; depth++) {
                int index = selectedStore[depth];
                Pos store = stores.get(index);
                distance = Math.min(distance, house.getDistance(store));
            }
            totalDistance += distance;
        }
        return totalDistance;
    }
}