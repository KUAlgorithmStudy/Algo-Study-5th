#include <iostream>
using namespace std;

int N, M, answer;
int grid[1001][1001];
int dp[1001][1001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> grid[i][j];
        }
    }

    dp[0][0] = grid[0][0];
    for (int i = 1; i < N; i++) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int i = 1; i < M; i++) {
        dp[0][i] = dp[0][i - 1] + grid[0][i];
    }

    for (int i = 1; i < N; i++) {
        for (int j = 1; j < M; j++) {
            dp[i][j] = grid[i][j] + max(dp[i - 1][j], dp[i][j - 1]);
        }
    }

    cout << dp[N - 1][M - 1];

    return 0;
}