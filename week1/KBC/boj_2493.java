import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

//BOJ 2493 [탑]
public class Main {
    public static class Tower {
        int height;
        int idx;
        public Tower(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Tower> stack = new Stack<>();
        int[] result = new int[n];

        int curIdx = n-1;
        stack.add(new Tower(heights[curIdx], curIdx));
        curIdx--;

        while(curIdx >= 0) {
            while(!stack.isEmpty() && heights[curIdx] >= stack.peek().height) {
                Tower t = stack.pop();
                result[t.idx] = curIdx + 1;
            }
            stack.add(new Tower(heights[curIdx], curIdx));
            curIdx--;
        }

        for(int i=0; i<n; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
