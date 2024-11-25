import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] studentsInClass = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int b = input[0];
        int c = input[1];

        long totalSupervisors = 0;
        for(int i=0; i<n; i++) {
            int remainingStudents = Math.max(studentsInClass[i] - b, 0);
            int assistantSupervisorCount = (remainingStudents + c - 1) / c;
            totalSupervisors += 1 + assistantSupervisorCount;
        }
        System.out.println(totalSupervisors);
    }
}
