import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] monSatNum = new boolean[300001];
    static boolean[] mon_sat_IsPrime;
    static PriorityQueue<Integer> Divisor;

    static void findDivisor(int input) {
        Divisor = new PriorityQueue<>();
        for (int i = 6; i <= input; i += i % 7 == 1 ? 5 : 2) {
            if (input % i != 0) continue;
            int tmp = input;
            while (tmp > 1) {
                tmp /= i;
                if (monSatNum[tmp] && mon_sat_IsPrime[i]) {
                    Divisor.add(i);
                    break;
                }
            }
        }
    }

    static void printDivisor(int findNum) {
        System.out.print(findNum + ":");
        while (!Divisor.isEmpty()) {
            System.out.print(" " + Divisor.poll());
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        //입력 queue에 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> input = new LinkedList<>();
        int tmp = Integer.parseInt(br.readLine());
        while (tmp != 1) {
            input.add(tmp);
            tmp = Integer.parseInt(br.readLine());
        }

        //월토 수 구하기
        for (int i = 1; i < 300000; i++) {
            if (i % 7 == 1 || i % 7 == 6)
                monSatNum[i] = true;
        }

        //월토 소수 구하기
        mon_sat_IsPrime = monSatNum.clone();
        mon_sat_IsPrime[1] = false;
        for (int i = 6; i * i < 300001; i += i % 7 == 1 ? 5 : 2) {
            if (monSatNum[i]) {
                for (int j = i * i; j <= 300000; j += i) {
                    mon_sat_IsPrime[j] = false;
                }
            }
        }

        //출력
        while (!input.isEmpty()) {
            int findNum = input.poll();
            findDivisor(findNum);
            printDivisor(findNum);
        }
    }
}
