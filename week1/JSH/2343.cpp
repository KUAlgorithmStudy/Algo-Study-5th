/**
 * N개의 강의를 M개의 CD에 나눠야 됨. 이때, 각 CD의 시간은 같고, 시간의 최솟값 구하기
 * -> cd의 길이를 기준으로 해보자.
 *
 * 풀케이스 -> 당연히 시간초과 -> 이분탐색
 * left = 1, right = 1000000000
 *
 * -> cd의 개수룰 cnt로 세고, M < cnt mid가 커짐
 *
 */
#include <iostream>
#include <algorithm>
using namespace std;

int N, M;
int lecture[1000001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int left = 0, right = 0;
    int mid, len, cnt;

    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        cin >> lecture[i];
        right += lecture[i];
    }

    left = *max_element(lecture, lecture + N);

    while (left <= right) {
        mid = (left + right) / 2;
        len = 0; cnt = 0;

        for (int i = 0; i < N; i++) {
            if (len + lecture[i] > mid) {
                len = 0;
                cnt++;
            }
            len += lecture[i];
        }

        if (len != 0) cnt++;
        if (cnt > M) left = mid + 1;
        else right = mid - 1;
    }

    cout << left;

    return 0;
}