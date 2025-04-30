import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Shark {
        int y;
        int x;
        int size;
        int time;
        int ate;

        public Shark(int y, int x, int size, int time, int ate) {
            this.y = y;
            this.x = x;
            this.time = time;
            if (ate >= size) {
                ate = 0;
                size++;
            }
            this.size = size;
            this.ate = ate;
        }
    }

    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Shark babyShark = null;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int k = 0; k < n; k++) {
                map[i][k] = input[k];
                if (map[i][k] == 9) {
                    babyShark = new Shark(i, k, 2, 0, 0);
                    map[i][k] = 0;
                }
            }
        }

        if (babyShark == null) {
            return;
        }

        int result = bfs(babyShark);
        System.out.println(result);
    }

    public static int bfs(Shark babyShark) {
        int[] dx = {0, -1, 1, 0};
        int[] dy = {-1, 0, 0, 1};
        Queue<Shark> queue = new LinkedList<>();
        Queue<Shark> canEat = new LinkedList<>();
        queue.add(babyShark);
        boolean[][] isVisit = new boolean[n][n];
        int aliveTime = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Shark current = queue.poll();
                isVisit[current.y][current.x] = true;
                for (int k = 0; k < 4; k++) {
                    int newY = current.y + dy[k];
                    int newX = current.x + dx[k];
                    if (isOutOfRange(newY, newX) || isVisit[newY][newX] || current.size < map[newY][newX]) { //지나갈수 없을 때
                        continue;
                    }
                    if (map[newY][newX] == 0 || map[newY][newX] == current.size) { //지나갈 수 있을 때
                        isVisit[newY][newX] = true;
                        queue.add(new Shark(newY, newX, current.size, current.time + 1, current.ate));
                    } else if (current.size > map[newY][newX]) { // 먹을 수 있을 때
                        isVisit[newY][newX] = true;
                        canEat.add(new Shark(newY, newX, current.size, current.time + 1, current.ate + 1));
                    }

                }
            }
            if (!canEat.isEmpty()) {
                Shark target = canEat.poll();
                while (!canEat.isEmpty()) {
                    Shark temp = canEat.poll();
                    if (temp.y < target.y) {
                        target = temp;
                    } else if (temp.y == target.y && temp.x < target.x) {
                        target = temp;
                    }
                }
                isVisit = new boolean[n][n];
                map[target.y][target.x] = 0;
                aliveTime = target.time;
                queue.clear();
                queue.add(target);
            }
        }
        return aliveTime;
    }

    private static boolean isOutOfRange(int newY, int newX) {
        return newY < 0 || newY >= n || newX < 0 || newX >= n;
    }
}
