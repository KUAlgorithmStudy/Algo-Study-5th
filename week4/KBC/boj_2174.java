import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Robot {
        int number;
        int x;
        int y;
        int angle;
        static int[][] map;

        public Robot(int number, int x, int y, String direction) {
            this.number = number;
            this.x = x;
            this.y = y;
            if (direction.equals("N")) {
                this.angle = 0;
            }
            if (direction.equals("E")) {
                this.angle = 90;
            }
            if (direction.equals("S")) {
                this.angle = 180;
            }
            if (direction.equals("W")) {
                this.angle = 270;
            }
        }

        public void order(String orderType) {
            if (orderType.equals("F")) {
                front();
            } else if (orderType.equals("L")) {
                left();
            } else if (orderType.equals("R")) {
                right();
            }
        }

        private void front() {
            deleteLog();
            if (angle == 0) {
                y += 1;
            } else if (angle == 90) {
                x += 1;
            } else if (angle == 180) {
                y -= 1;
            } else if (angle == 270) {
                x -= 1;
            }
            writeLog();
        }

        private void left() {
            angle = angle - 90;
            if (angle < 0) {
                angle += 360;
            }
        }

        private void right() {
            angle = (angle + 90) % 360;
        }

        private void deleteLog() {
            map[y][x] = 0;
        }

        private void writeLog() {
            if (isOutOfMax()) {
                throw new IllegalArgumentException("Robot " + number + " crashes into the wall");
            } else if (isCrash()) {
                throw new IllegalArgumentException("Robot " + number + " crashes into robot " + map[y][x]);
            } else {
                map[y][x] += this.number;
            }
        }


        private boolean isCrash() {
            return map[y][x] != 0;
        }

        private boolean isOutOfMax() {
            return x >= map[0].length || y >= map.length || x < 1 || y < 1;
        }
    }

    static String[] input;
    static Robot[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        int width = Integer.parseInt(input[0]);
        int height = Integer.parseInt(input[1]);
        input = br.readLine().split(" ");
        int robotCount = Integer.parseInt(input[0]);
        int orderCount = Integer.parseInt(input[1]);

        Robot.map = new int[height + 1][width + 1];
        robots = new Robot[robotCount + 1];
        for (int i = 1; i <= robotCount; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            String direction = input[2];
            robots[i] = new Robot(i, x, y, direction);
            Robot.map[y][x] = i;
        }

        for (int i = 0; i < orderCount; i++) {
            input = br.readLine().split(" ");
            int nRobot = Integer.parseInt(input[0]);
            String orderType = input[1];
            int repeat = Integer.parseInt(input[2]);
            try {
                for (int k = 0; k < repeat; k++) {
                    robots[nRobot].order(orderType);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("OK");
    }
}
