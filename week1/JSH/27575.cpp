/**
 * 1번줄: 남은 초콜릿의 총 개수, 7H 작성.
 * -> 직전 단계에 남아있던 초콜릿의 총 개수의 일의 자리 값을 진법으로 적용
 * -> 0, 1이면 10진법
 *
 * 2번줄: 남은게 많은 순서대로 알파벳 공백없이 출력
 * -> 남은게 없으면 작성 x
 * -> 같으면 알파벳 순으로 정렬
 * -> 남은게 0개면 NULL
 *
 */

#include <iostream>
#include <algorithm>
using namespace std;

int H, T, C, K, G, M;
int h, t, c, k, g;

bool compare(pair<char, int> &a, pair<char, int> &b) {
    if (a.second == b.second) {
        return a.first - 'A' < b.first - 'A';
    }
    else return a.second > b.second;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> H >> T >> C >> K >> G;
    cin >> M;

    int formation = (H + T + C + K + G) % 10;

    for (int i = 0; i < M; i++) {
        cin >> h >> t >> c >> k >> g;

        H -= h; T -= t; C -= c; K -= k; G -= g;
        int total = H + T + C + K + G;

        if (1 < formation) {
            string str_total = "";
            while (total) {
                str_total += total % formation + '0';
                total /= formation;
            }
            if (str_total == "") str_total = "0";
            reverse(str_total.begin(), str_total.end());
            cout << str_total << "7H\n";
        }
        else cout << total << "7H\n";

        total = H + T + C + K + G;
        formation = total % 10;

        if (total == 0) {
            cout << "NULL\n";
        }
        else {
            pair<char, int> choco[5] = { {'H', H}, {'T', T}, {'C', C}, {'K', K}, {'G', G} };
            sort(choco, choco + 5, compare);

            for (int i = 0; i < 5; i++) {
                if (choco[i].second == 0) continue;
                cout << choco[i].first;
            }
            cout << '\n';
        }

    }


    return 0;
}