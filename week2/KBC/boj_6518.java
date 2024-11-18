import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> dic = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nDict = Integer.parseInt(br.readLine());
        for (int i = 0; i < nDict; i++) {
            dic.add(br.readLine());
        }

        int nInput = Integer.parseInt(br.readLine());
        for (int i = 0; i < nInput; i++) {
            String input = br.readLine();
            String origin = "";
            if (validCorrected(input)) {
                System.out.println(input + " is correct");
            } else if (!(origin = validMisspell(input)).isEmpty()) {
                System.out.println(input + " is a misspelling of " + origin);
            } else {
                System.out.println(input + " is unknown");
            }
        }
    }

    public static boolean validCorrected(String input) {
        for (String s : dic) {
            if (input.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static String validMisspell(String input) {
        for (String s : dic) {
            if (s.length() != input.length()) {//길이 다를 때
                String max = maxString(s, input);
                String min = minString(s, input);
                int errorCount = 0;

                for (int i = 0; i < min.length() && errorCount <= 1; i++) {
                    if (max.charAt(i + errorCount) != min.charAt(i)) {
                        errorCount++;
                        i--;
                    }
                }
                if (errorCount <= 1) return s;
            } else { // 길이 같을 때
                int errorCount = 0;
                int lastErrorIndex = 0;
                for (int i = 0; i < s.length() && errorCount <= 1; i++) {
                    if (s.charAt(i) != input.charAt(i)) {
                        errorCount++;
                        lastErrorIndex = i;
                    }
                }
                if (errorCount == 1) return s;
                if (errorCount == 2) {
                    if (s.charAt(lastErrorIndex - 1) == input.charAt(lastErrorIndex) && s.charAt(lastErrorIndex) == input.charAt(lastErrorIndex - 1))
                        return s;
                }
            }
        }
        return "";
    }

    public static String maxString(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return s2;
        }
        return s1;
    }

    public static String minString(String s1, String s2) {
        if (s1.length() >= s2.length()) {
            return s2;
        }
        return s1;
    }
}