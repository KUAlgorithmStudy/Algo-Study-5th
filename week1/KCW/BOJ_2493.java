// BOJ_2493  : 탑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
    static int N;
    static int[] arr, solution;
    static Stack<Integer> stk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        solution = new int[N];
        stk = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = N-1; i>= 0;i--) {
            while (!stk.isEmpty() && arr[stk.peek()] < arr[i]) {
                int n = stk.pop();
                solution[n] = i + 1;
            }
            stk.push(i);
        }

        for(int i = 0; i<solution.length; i++){
            if(i+1 == solution.length)
                System.out.println(solution[i]);
            else System.out.print(solution[i]+" ");
        }
    }
}
