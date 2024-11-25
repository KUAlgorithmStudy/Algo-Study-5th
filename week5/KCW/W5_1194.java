import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//1194 달이 차오른다, 가자.
/*
조건 1. 빈칸은 언제나 이동 가능, 벽은 절대 이동 불가능
조건 2. 열쇠가 있을 때만 문을 지날 수 있음
조건 3. 출구는 한 개가 아님
조건 4. 같은 열쇠나 문이 여러 번 나올 수 있음, 문에 대응하는 열쇠가 없을 수도 있음
조건 5. 열쇠는 여러 번 사용할 수 있음

사용 방법 : BFS -> 메모리초과
대안 : visited배열과 hasKey 배열을 비트마스킹 방법으로 표현 -> 메모리초과
대안 : hasKey 숫자가 여러 배리에이션으로 다양해졌으니 visited 배열을 전역변수로 두고 관리
-> 다음의 과정으로 풀 수 있었음
 */
public class Main {
    static int n, m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Character[][] map;
    static boolean[][][] visited;

    static class Stat {
        int x, y, depth, hasKey;

        Stat(int x, int y, int depth, int hasKey) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.hasKey = hasKey;
        }

        boolean unlocked(char ch) {
            return (hasKey & (1 << (ch - 'A'))) != 0; //0100가 반환된다면 이 값은 4임
        }
    }

    static int bfs(int x, int y) {
        Queue<Stat> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][1 << 6]; //6은 열쇠 개수
        q.add(new Stat(x, y, 1, 0));
        visited[y][x][0] = true;

        while (!q.isEmpty()) {
            Stat p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 경계 조건 & 벽이거나 & 방문한 적 있으면 continue
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || map[ny][nx] == '#' || visited[ny][nx][p.hasKey]) continue;

                if (map[ny][nx] == '1') {
                    return p.depth;
                }

                // 열쇠를 줍는 경우
                if (map[ny][nx] >= 'a' && map[ny][nx] <= 'f') {
                    int keyIdx = 1 << (map[ny][nx] - 'a');
                    //열쇠가 이미 있는 경우
                    if((p.hasKey & keyIdx) == keyIdx ){
                        visited[ny][nx][p.hasKey] = true;
                        q.add(new Stat(nx, ny, p.depth + 1, p.hasKey));
                    } else{ //열쇠를 처음 얻는 경우
                        int newHasKey = p.hasKey | keyIdx; // 복사본 생성
                        visited[ny][nx][newHasKey] = true;
                        visited[ny][nx][p.hasKey] = true;

                        q.add(new Stat(nx, ny, p.depth + 1, newHasKey));
                    }
                }
                // 문인 경우
                if (map[ny][nx] >= 'A' && map[ny][nx] <= 'F' && p.unlocked(map[ny][nx])) {
                    visited[ny][nx][p.hasKey] = true;
                    q.add(new Stat(nx, ny, p.depth + 1, p.hasKey));
                }

                // 열쇠 없을 때 이동
                if (map[ny][nx] == '.') {
                    visited[ny][nx][p.hasKey] = true;
                    q.add(new Stat(nx, ny, p.depth + 1, p.hasKey));
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
        map = new Character[n][m];
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (str.charAt(j) == '0') {
                    x = j;
                    y = i;
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(x, y));
    }
}
