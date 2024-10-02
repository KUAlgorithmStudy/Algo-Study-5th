import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_27535 {
    static int M;
    static chocolate[] chocolates = new chocolate[5];
    static chocolate[] sort_choco = new chocolate[5];
    static char[] cho_name = {'H', 'T', 'C', 'K', 'G'};

    static class chocolate {
        char name;
        int number;

        public chocolate(char name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            chocolates[i] = new chocolate(cho_name[i], Integer.parseInt(st.nextToken()));
        }
        M = Integer.parseInt(br.readLine());
        int prev_sum = 0;
        for (int i = 0; i < 5; i++) {
            prev_sum += chocolates[i].number;
        }

        //풀이
        int sum_chocolate;
        for (int i = 0; i < M; i++) {
            sum_chocolate = 0;
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 5; k++) {
                chocolates[k].number -= Integer.parseInt(st.nextToken());
                sum_chocolate += chocolates[k].number;
            }

            print_total_choco(sum_chocolate, prev_sum);
            print_sorted_choco();
            prev_sum = sum_chocolate;

//            if(sum_chocolate == 0)
//                System.out.println("NULL");
//            else{
//                print_sorted_choco();
//            }
        }
    }

    static void print_total_choco(int total, int prev) {
        //이전꺼 일의자리수
        int radix = prev % 10;
        if (radix == 0 || radix == 1) {
            radix = 10;
        }
        String str = Integer.toString(total, radix);
        System.out.println(str + "7H");
    }

    static void print_sorted_choco() {
        sort_choco = chocolates.clone();
        Arrays.sort(sort_choco, (x, y) -> {
            if (x.number == y.number)
                return x.name - y.name;
            return y.number - x.number;
        });

        if (sort_choco[0].number == 0)
            System.out.print("NULL");
        else
            for (chocolate tmp : sort_choco) {
                if (tmp.number == 0)
                    break;
                System.out.print(tmp.name);
            }
        System.out.println();
    }
}