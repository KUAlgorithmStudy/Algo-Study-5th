import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

//BOJ 1939 중량제한
public class Main {
    static boolean[] isVisit;
    static int[] prevWeight;
    static List<List<Island>> paths;
    static int maxWeight;

    public static class Island {

        int number;
        int weight;

        public Island(int target, int weight) {
            this.number = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //초기 데이터 n, m
        int[] set = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //이전까지 도달했던 최대중량 값
        prevWeight = new int[set[0] + 1];
        //최대 중량
        maxWeight = 0;
        isVisit = new boolean[set[0] + 1];

        //다음 섬의 이름과 중량에 대한 정보를 담은 Graph클래스
        //Graph클래스를 2차원배열로 선언, 각 섬마다 갈 수 있는 다음 섬을 담을 것
        paths = new ArrayList<>();
        for (int i = 0; i <= set[0]; i++) {
            paths.add(new ArrayList<>());
        }


        for (int i = 0; i < set[1]; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int source = input[0];
            int target = input[1];
            int weight = input[2];

            //양방향 그래프
            paths.get(source).add(new Island(target, weight));
            paths.get(target).add(new Island(source, weight));
        }

        //최대중량을 구하고자 하는 경로
        int[] path = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int source = path[0];
        int target = path[1];

        dfs(source, target, Integer.MAX_VALUE);
        System.out.println(maxWeight);
    }

    //현재 위치, 목표 위치, 지금 까지 거친 경로에서 가능한 최대중량
    public static void dfs(int source, int target, int weight) {
        if (source == target) {
            maxWeight = max(maxWeight, weight);
            return;
        }

        if (!isVisit[source] && weight > prevWeight[source]) {
            isVisit[source] = true;
            prevWeight[source] = weight;
            for (Island island : paths.get(source)) {
                dfs(island.number, target, min(weight, island.weight));
            }
            isVisit[source] = false;
        }
    }
}