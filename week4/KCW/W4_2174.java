import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2174. 로봇 시뮬레이션

public class Main {
    static int A, B, N, M;
    static Robot[] robots;

    public static class Robot {
        int x;
        int y;
        String direction;

        Robot(int x, int y, String d) {
            this.x = x;
            this.y = y;
            direction = d;
        }

        void move() {
            if (direction.equals("N")) y++;
            else if (direction.equals("S")) y--;
            else if (direction.equals("E")) x++;
            else if (direction.equals("W")) x--;
        }

        void turnLeft() {
            if (direction.equals("N")) direction = "W";
            else if (direction.equals("W")) direction = "S";
            else if (direction.equals("S")) direction = "E";
            else if (direction.equals("E")) direction = "N";
        }

        void turnRight() {
            if (direction.equals("N")) direction = "E";
            else if (direction.equals("E")) direction = "S";
            else if (direction.equals("S")) direction = "W";
            else if (direction.equals("W")) direction = "N";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Robot)) return false;
            Robot r = (Robot) obj;
            return x == r.x && y == r.y;
        }
    }

    static void checkCrashedWall(int idx) {
        Robot robot = robots[idx];
        if (robot.x <= 0 || robot.y <= 0 || robot.x > A || robot.y > B)
            throw new RuntimeException("Robot " + idx + " crashes into the wall");
    }

    static void checkBumpedRobot(int idx) {
        Robot robot = robots[idx];
        for (int i = 1; i <= N; i++) {
            if (i != idx && robot.equals(robots[i]))
                throw new RuntimeException("Robot " + idx + " crashes into robot "+i);
        }

    }

    static void executeCommand(int idx, String command, int repeat) {
        for(int i = 0; i<repeat; i++){
            if (command.equals("L")) robots[idx].turnLeft();
            else if (command.equals("R")) robots[idx].turnRight();
            else if (command.equals("F")) {
                robots[idx].move();
            }
            checkBumpedRobot(idx);
            checkCrashedWall(idx);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robots = new Robot[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            robots[i] = new Robot(x, y, direction);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int repeat = Integer.parseInt(st.nextToken());
            try {
                executeCommand(index, command, repeat);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("OK");
    }
}
