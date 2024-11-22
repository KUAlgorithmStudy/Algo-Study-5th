import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2564 경비원
public class Main {
    static int w, h, n;

    static class Point {
        int dist, len;

        Point(int dist, int len) {
            this.dist = dist;
            this.len = len;
        }
    }

    static int findDist(Point p1, Point p2) {
        int result = 0;
        if (p1.dist > p2.dist) p2 = swap(p1, p1 = p2);
        if (p1.dist == p2.dist) {
            result = Math.abs(p1.len - p2.len);
        } else if (p1.dist == 1 && p2.dist == 2) {
            int tmp = h + p1.len + p2.len;
            result = Math.min(tmp, w * 2 + h * 2 - tmp);
        } else if(p1.dist == 1 && p2.dist == 3){
            result = p1.len + p2.len;
        } else if(p1.dist == 1 && p2.dist == 4){
            result = p2.len + (w - p1.len);
        } else if(p1.dist == 2 && p2.dist == 3){
            result = p1.len + (h - p2.len);
        } else if(p1.dist == 2 && p2.dist == 4){
            result = w + h - p1.len - p2.len;
        } else if(p1.dist == 3 && p2.dist == 4){
            int tmp = w + p1.len + p2.len;
            result = Math.min(tmp, w*2+h*2- tmp);
        }
        return result;
    }

    static Point swap(Point p1, Point p2) {
        return p1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            pts[i] = new Point(dist, len);
        } //입력
        st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());
        Point dongen = new Point(dist, len);

        int sum = 0;
        for(int i = 0; i<n; i++){
            sum += findDist(dongen, pts[i]);
        }
        System.out.println(sum);
    }
}
