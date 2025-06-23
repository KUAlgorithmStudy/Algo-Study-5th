import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//16236 아기상어
/*
fish 클래스
findFish 함수(int x, int y, int size)
먹는 기작은 다음과 같다.
1. find로 가장 가까운 fish를 찾는다.
2. 아기상어의 크기, x, y를 바꾸고 결과값을 저장한다
3. 1번부터 반복한다.

find 함수
1. 아기상어보다 큰 물고기는 벽이다. 지나갈 수 없다.
2. 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
3. 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
 */
public class Main {
    static int n;
    static int[][] map;

    static class Fish implements Comparable<Fish> {
        int x, y, size, depth;

        Fish(int x, int y, int size, int depth) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.depth = depth;
        }

        @Override
        public int compareTo(Fish o) {
            if (depth == o.depth) {
                if (y == o.y) {
                    return x - o.x;
                }
                return y - o.y; //위에 있어야 함, 0과 2가 있을 때 0 - 2 음수 그대로
            }
            return depth - o.depth;//음수가 나오면 맞는 방향, 양수가 나오면 방향을 바꿔줌
        }
    }

    static class LittleShark extends Fish {
        int ateFish;
        LittleShark(int x, int y, int size, int depth) {
            super(x, y, size, depth);
            ateFish = 0;
        }

        public void eat(Fish fish){
            x = fish.x;
            y = fish.y;
            depth += fish.depth;
            ateFish++;
            if(ateFish == size) {
                size++;
                ateFish = 0;
            }
            map[y][x] = 0;
        }
    }

    static Fish find(Fish littleShark) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        boolean[][] visited = new boolean[n][n];
        int[][] fishMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            fishMap[i] = map[i].clone(); // 깊은 복사
        }
        Queue<Fish> q = new LinkedList<>();
        ArrayList<Fish> fishes = new ArrayList<>();

        q.add(littleShark);
        visited[littleShark.y][littleShark.x] = true;

        while (!q.isEmpty()) {
            Fish fish = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = fish.x + dx[i];
                int ny = fish.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[ny][nx]) continue;

                if (fishMap[ny][nx] == 0 || fishMap[ny][nx] == littleShark.size) {
                    //물고기가 없거나 물고기가 아기상어와 같은 크기일 때 지나서 감
                    visited[ny][nx] = true;
                    q.add(new Fish(nx, ny, fish.size, fish.depth + 1));
                } else if (fishMap[ny][nx] > littleShark.size) {
                    visited[ny][nx] = true;
                    //물고기가 아기상어보다 클 때 : 돌아서 감, 아무것도 큐에 넣지 않음
                } else if (fishMap[ny][nx] < littleShark.size) {
                    //아기상어가 먹을 수 있는 물고기일 때 : 먹을 수 있는 후보에 들어감
                    visited[ny][nx] = true;
                    fishes.add(new Fish(nx, ny, fishMap[ny][nx], fish.depth + 1));
                    fishMap[ny][nx] = 0;
                }
            }
        }

        if (fishes.isEmpty()) return null;
        Collections.sort(fishes);
        return fishes.get(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        LittleShark littleShark = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    littleShark = new LittleShark(j, i, 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        Fish foodFish = find(littleShark);
        while (foodFish != null) {
            littleShark.eat(foodFish);
            Fish newLittleShark = new Fish(littleShark.x, littleShark.y, littleShark.size, 0);
            foodFish = find(newLittleShark);
        }

        System.out.println(littleShark.depth);
    }
}
