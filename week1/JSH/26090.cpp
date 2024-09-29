#include <iostream>
using namespace std;

int N, answer = 0;
int seqeunce[2001], psum[2001];
bool prime[1000001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;
    for (int i = 0; i < N; i++) cin >> seqeunce[i];

    psum[0] = seqeunce[0];
    for (int i = 1; i < N; i++) {
        psum[i] = seqeunce[i] + psum[i - 1];
    }

    for (int i = 2; i <= 1000000; i++) prime[i] = true;
    for (int i = 2; i * i <= 1000000; i++) {
        if (prime[i]) {
            for (int j = i * i; j <= 1000000; j += i) {
                prime[j] = false;
            }
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = i; j < N; j++) {
            int sub_len = j - i + 1;
            int sub_sum;

            if (i == 0) sub_sum = psum[j];
            else sub_sum = psum[j] - psum[i - 1];

            if (prime[sub_len] && prime[sub_sum]) answer++;
        }
    }

    cout << answer;

    return 0;
}