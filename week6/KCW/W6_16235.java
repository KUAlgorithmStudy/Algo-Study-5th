import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//16235 나무 재테크
public class Main {
    static int n, m, k;
    static int[][] a;
    static Cube[][] land;

    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[n][n];
        land = new Cube[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                land[i][j] = new Cube();
            }
        }

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                a[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            land[x][y].trees.add(z);
        }
        //입력

        //처리
        for(int i = 0; i<k; i++){
            spring();
            summer();
            autumn();
            winter();
        }

        //출력
        System.out.println(getSumOfAllTree());
    }

    public static int getSumOfAllTree() {
        int sum = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                sum += land[r][c].countTrees();
            }
        }
        return sum;
    }

    public static void spring() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                land[r][c].growTree();
            }
        }
    }

    public static void summer() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                land[r][c].addNourishment();
            }
        }
    }

    public static void autumn() {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int cnt = land[r][c].countBreedingTree();
                for (int i = 0; i < cnt; i++) {
                    for (int d = 0; d < 8; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                            land[nr][nc].trees.add(1);
                        }
                    }
                }
            }
        }
    }

    public static void winter() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                land[r][c].nourishment += a[r][c];
            }
        }
    }

    static class Cube {
        List<Integer> trees;
        int nourishment;
        int deadTrees;

        Cube() {
            trees = new LinkedList<>();
            nourishment = 5;
            deadTrees = 0;
        }

        void growTree() {
            Collections.sort(trees);
            List<Integer> newTrees = new LinkedList<>();
            for (int tree : trees) {
                if (nourishment >= tree) {
                    nourishment -= tree;
                    newTrees.add(tree + 1);
                } else {
                    deadTrees += tree / 2;
                }
            }
            trees = newTrees;
        }

        void addNourishment() {
            nourishment += deadTrees;
            deadTrees = 0;
        }

        int countBreedingTree() {
            int breedingCnt = 0;
            for (int tree : trees) {
                if (tree % 5 == 0) breedingCnt++;
            }
            return breedingCnt;
        }

        int countTrees() {
            return trees.size();
        }
    }
}