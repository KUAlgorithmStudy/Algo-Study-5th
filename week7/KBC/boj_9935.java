import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//BOJ 9935 [문자열 폭발]
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        String sentence = br.readLine();
        String bomb = br.readLine();

        for (char ch : sentence.toCharArray()) {
            stack.add(ch);
            if (stack.size() >= bomb.length()) {
                boolean isBombed = true;
                for (int i = 0; i < bomb.length(); i++) {
                    if (stack.get((stack.size() - bomb.length()) + i) != bomb.charAt(i)) {
                        isBombed = false;
                        break;
                    }
                }
                if (isBombed) {
                    for (int i = 0; i < bomb.length(); i++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for(char ch : stack) {
                sb.append(ch);
            }
            System.out.println(sb);
        }
    }
}
