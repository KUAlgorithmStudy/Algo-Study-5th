#include <iostream>
#include <queue>
using namespace std;

int N, answer = 0;
priority_queue<int> pq;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int dasom, tmp;

    cin >> N;
    cin >> dasom;

    for (int i = 0; i < N - 1; i++) {
        cin >> tmp;
        pq.push(tmp);
    }

    if (!pq.empty()) {
        while (dasom <= pq.top()) {
            dasom++;
            int top_pq = pq.top();
            pq.pop();
            pq.push(top_pq - 1);

            answer++;
        }
    }

    cout << answer;

    return 0;
}