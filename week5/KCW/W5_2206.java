import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//2206 벽 부수고 이동하기
public class Main {
    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] map;
    static boolean[][][] visited;

    static class Point {
        int x, y, depth;
        boolean hasBroken;

        Point(int x, int y, int depth, boolean hasBroken) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.hasBroken = hasBroken;
        }
    }

    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1, false));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == n - 1 & p.y == m - 1) {
                return p.depth;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 경계 조건
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 벽을 부순 적이 없는 경우
                if (map[nx][ny] == 1 && !p.hasBroken && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    q.add(new Point(nx, ny, p.depth + 1, true));
                }

                // 일반적인 이동
                if (map[nx][ny] == 0 && !visited[nx][ny][p.hasBroken ? 1 : 0]) {
                    visited[nx][ny][p.hasBroken ? 1 : 0] = true;
                    q.add(new Point(nx, ny, p.depth + 1, p.hasBroken));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }
}
