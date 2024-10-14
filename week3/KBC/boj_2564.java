import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//BOJ 2564 경비원

public class Main {
    public static class Position {
        int direction;
        int distance;

        public Position(int direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }
    }

    static int height;
    static int width;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        width = input[0];
        height = input[1];
        int n = Integer.parseInt(br.readLine());
        Position[] stores = new Position[n];
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            stores[i] = new Position(input[0], input[1]);
        }
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Position person = new Position(input[0], input[1]);


        int result = 0;
        for (int i = 0; i < n; i++) {
            int personDistance = getClockWiseDistance(person);
            int storeDistance = getClockWiseDistance(stores[i]);
            int total = 2 * (height + width);
            int clockwiseDistance = Math.abs(personDistance - storeDistance);
            int counterClockwiseDistance = total - clockwiseDistance;
            result += Math.min(clockwiseDistance, counterClockwiseDistance);
        }
        System.out.println(result);
    }

    public static int getClockWiseDistance(Position person) {
        if (person.direction == 1) {
            return person.distance;
        } else if (person.direction == 4) {
            return width + person.distance;
        } else if (person.direction == 2) {
            return width + height + (width - person.distance);
        } else {
            return width + height + width + height - person.distance;
        }
    }
}
