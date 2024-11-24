import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX = 1000000;
    static int[][] map;
    static int height;
    static int width;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);
        map = new int[height][width];
        for (int h = 0; h < height; h++) {
            int[] numbers = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int w = 0; w < width; w++) {
                map[h][w] = numbers[w];
            }
        }

        int[][] distanceMapToStart = bfs(0, 0);
        int[][] distanceMapToEnd = bfs(width - 1, height - 1);
        int minDistance = MAX;
        if(distanceMapToStart[height-1][width-1] != 0) {
            minDistance = distanceMapToStart[height-1][width-1];
        }
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (map[h][w] == 1) {
                    int min_start = MAX;
                    int min_end = MAX;
                    for (int i = 0; i < 4; i++) {
                        int newX = w + dx[i];
                        int newY = h + dy[i];
                        if (newX < 0 || newX >= width || newY < 0 || newY >= height || map[newY][newX] == 1) {
                            continue;
                        }
                        int distanceStart = distanceMapToStart[h + dy[i]][w + dx[i]];
                        int distanceEnd = distanceMapToEnd[h + dy[i]][w + dx[i]];
                        if (distanceStart != 0) {
                            min_start = Math.min(distanceStart, min_start);
                        }
                        if (distanceEnd != 0) {
                            min_end = Math.min(distanceEnd, min_end);
                        }
                    }
                    minDistance = Math.min(minDistance, min_start + min_end + 1);
                }
            }
        }
        if (minDistance == MAX) {
            System.out.println(-1);
            return;
        }
        System.out.println(minDistance);
    }

    public static int[][] bfs(int startX, int startY) {
        int[][] distanceMap = new int[height][width];
        int distance = 1;
        Queue<Pos> queue = new LinkedList<>();
        if (map[startY][startX] != 1) {
            queue.add(new Pos(startX, startY));
            distanceMap[startY][startX] = distance;
            distance++;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pos curPosition = queue.poll();
                for (int direct = 0; direct < 4; direct++) {
                    int newX = curPosition.x + dx[direct];
                    int newY = curPosition.y + dy[direct];
                    if (newX < 0 || newX >= width || newY < 0 || newY >= height || distanceMap[newY][newX] != 0 || map[newY][newX] == 1) {
                        continue;
                    }
                    distanceMap[newY][newX] = distance;
                    queue.add(new Pos(newX, newY));
                }
            }
            distance++;
        }
        return distanceMap;
    }
}
