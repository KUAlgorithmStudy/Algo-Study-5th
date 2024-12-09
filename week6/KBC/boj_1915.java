import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int height;
    static int width;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);
        map = new int[height + 1][width + 1];
        for (int h = 1; h <= height; h++) {
            input = br.readLine().split("");
            for (int w = 1; w <= width; w++) {
                map[h][w] = Integer.parseInt(input[w - 1]);
            }
        }

        int max = 0;
        for (int h = 1; h <= height; h++) {
            for (int w = 1; w <= width; w++) {
                if (map[h][w] >= 1 && map[h][w - 1] >= 1 && map[h - 1][w] >= 1 && map[h - 1][w - 1] >= 1) {
                    map[h][w] = Math.min(map[h][w - 1], Math.min(map[h - 1][w], map[h-1][w-1])) + 1;
                }
                max = Math.max(map[h][w], max);
            }
        }
        System.out.println(max * max);
    }
}
