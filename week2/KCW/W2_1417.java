import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.exit;

public class Main {
    static int N, people;
    static Integer[] voted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N == 1) {//예외처리
            System.out.println(0);
            exit(0);
        }
        voted = new Integer[N - 1];
        int myVoted = Integer.parseInt(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            voted[i] = Integer.parseInt(br.readLine());
        }

        //계산 및 출력
        Arrays.sort(voted, (x, y) -> y - x);
        while (myVoted <= voted[0]) {
            people++;
            myVoted++;
            voted[0]--;
            Arrays.sort(voted, (x, y) -> y - x);
        }

        System.out.println(people);
    }
}