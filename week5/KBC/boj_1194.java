import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Data {
        int y;
        int x;
        int count;
        int keys;
        int prevX;
        int prevY;

        public Data(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
            keys = 0b0;
        }

        public Data(Data data) {
            this.y = data.y;
            this.x = data.x;
            this.count = data.count;
            this.keys = data.keys;
            this.prevX = data.prevX;
            this.prevY = data.prevY;
        }

        public void pickKey(int key) {
            keys = keys | key;
        }

        public boolean isPickedKey(int key) {
            return (keys & key) > 0;
        }
    }

    static boolean[][][] visit;
    static final int[] dy = {0, -1, 0, 1};
    static final int[] dx = {-1, 0, 1, 0};
    static String[][] map;
    static int height;
    static int width;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);
        map = new String[height][width];
        visit = new boolean[64][height][width];
        Data start = null;
        for (int y = 0; y < height; y++) {
            input = br.readLine().split("");
            for (int x = 0; x < width; x++) {
                map[y][x] = input[x];
                if (map[y][x].equals("0")) {
                    start = new Data(y, x, 0);
                    map[y][x] = ".";
                }
            }
        }

        if (start != null) {
            System.out.println(bfs(start));
            return;
        }
        System.out.println("시작 위치가 없습니다.");
    }

    public static int bfs(Data start) {
        Queue<Data> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Data data = queue.poll();
                for (int k = 0; k < 4; k++) {
                    Data newData = new Data(data);
                    newData.y += dy[k];
                    newData.x += dx[k];
                    newData.count += 1;

                    if (outOfRange(newData)) {
                        continue;
                    }
                    String curTile = map[newData.y][newData.x];
                    if (visit[newData.keys][newData.y][newData.x] || curTile.equals("#")) {
                        continue;
                    }
                    visit[newData.keys][newData.y][newData.x] = true;
                    if (curTile.equals("1")) {
                        return newData.count;
                    }
                    if (curTile.matches("^[a-f]$")) { //열쇠
                        newData.pickKey(0b1 << (curTile.charAt(0) - 'a'));
                        queue.add(newData);
                    } else if (curTile.matches("^[A-F]$")) { //문
                        if (newData.isPickedKey(0b1 << (curTile.charAt(0) - 'A'))) {
                            queue.add(newData);
                        }
                    } else if (curTile.equals(".")) {
                        queue.add(newData);
                    }
                }
            }
        }
        return -1;
    }

    private static boolean outOfRange(Data newData) {
        return newData.y < 0 || newData.y >= map.length || newData.x < 0 || newData.x >= map[0].length;
    }
}
