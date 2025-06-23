/**
 * 게임이론: 쉬운 유형은 대부분 dp로 처리 가능
 * -> 그런데 규칙 찾는게 더 쉬움
 * 
 * box1: 돌이 N개, box2: 돌이 M개 있음
 * -> 두 사람이 턴 돌아가면서 게임
 * 
 * 각 사람은 하나의 박스 선택 -> 돌을 전부 비우고, 다른 박스의 돌을 적절히 두 박스로 분배
 * -> 두 박스에는 적어도 1개 이상의 돌이 필요함
 * -> 이 룰 깨지면 지는거임
 * 
 * 홀수개를 남기면 -> 두 박스는 홀 + 짝 : 다음 사람이 짝 날리면 
 * -> 짝수 있으면 A가 이김. 아니면 B 승
 * 
 */

// #include <iostream>
// using namespace std;

// int N, M;

// int main() {
//     ios::sync_with_stdio(false);
//     cin.tie(0);

//     cin >> N >> M;
//     if (N % 2 == 0 || M % 2 == 0) cout << 'A';
//     else cout << 'B';

//     return 0;
// }

/**
 * DP 풀이
 * dp[box1][box2] -> a를 골랐을 때, b에서 지는 경우 -> a가 이김
 * -> N, M 이하 모든 경우를 탐색해서 b에서 지는 경우가 생기면 a가 이기고, 아니면 b가 이김
 */

#include <iostream>
#include <cstring>
using namespace std;

int N, M;
int dp[102][102];

bool dfs(int x, int y) {
    if (x == 2 || y == 2) return true;
    if(dp[x][y] != -1) return dp[x][y];

    bool tmp_answer = false;

    for(int i = 1; i < x; i++) {
        tmp_answer |= !(dfs(i, x - i));
    }
    for(int i = 1; i < y; i++) {
        tmp_answer |= !(dfs(i, y - i));
    }

    dp[x][y] = tmp_answer;

    return tmp_answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    memset(dp, -1, sizeof(dp));

    cin >> N >> M;
    
    if(dfs(N, M)) cout << 'A';
    else cout << 'B';

    return 0;
}