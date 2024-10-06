import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//6518 오타 자동 수정
public class Main {
    static int n, q;
    static String[] wordDictionary;
    static String[] output;

    static int correctMisspelling(String str) {
        //같다면 리턴
        for (String word : wordDictionary)
            if (word.equals(str)) {
                return -2;
            }
        //고칠 수 있는 오타인경우 리턴
        for (int i = 0; i < n; i++) {
            if (isMisspelled(wordDictionary[i], str))
                return i;
        }
        //아무것도 아닐 경우 알 수 없는 단어
        return -1;
    }

    static boolean isMisspelled(String word, String str) {
        boolean result = false;
        int sameChar;
        if (word.length() - str.length() == 1) {//한 글자 적게 썼을 때
            sameChar = str.length();
            for (int i = 0, j = 0; i < word.length() && j < str.length(); i++, j++) {
                if (word.charAt(i) == str.charAt(j)) sameChar--;
                else j--;
            }
            if (sameChar == 0) result = true;
        } else if (word.length() - str.length() == -1) {//한 글자 많게 썼을 때
            sameChar = word.length();
            for (int i = 0, j = 0; i < word.length() && j<str.length(); i++, j++) {
                if (word.charAt(i) == str.charAt(j)) sameChar--;
                else i--;
            }
            if (sameChar == 0) result = true;
        } else if (word.length() == str.length()) {
            sameChar = 0;
            int diffIdx = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == str.charAt(i)) sameChar++;
                else diffIdx = i;
            }
            if(sameChar+1 == word.length()) result = true; // 한 글자를 잘못적었을 때
            if(sameChar+2 == word.length() && word.charAt(diffIdx) == str.charAt(diffIdx-1) &&
            word.charAt(diffIdx-1) == str.charAt(diffIdx)) result = true; // 글자를 바꿔적었을 때
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        wordDictionary = new String[n];
        for (int i = 0; i < n; i++) {
            wordDictionary[i] = br.readLine();
        }
        q = Integer.parseInt(br.readLine());
        output = new String[q];
        for (int i = 0; i < q; i++) {
            output[i] = br.readLine();
        }//입력끝

        //correctMisspelling 이용해 검사 후 실시간 출력
        int stat;
        for (int i = 0; i < q; i++) {
            stat = correctMisspelling(output[i]);
            if (stat == -2)
                System.out.println(output[i] + " is correct");
            else if (stat == -1)
                System.out.println(output[i] + " is unknown");
            else System.out.println(output[i] + " is a misspelling of " + wordDictionary[stat]);
        }
    }
}
