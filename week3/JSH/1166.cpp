#include <iostream>
#define ll long long
#define ld long double
using namespace std;

int N, L, W, H;
ld A;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> L >> W >> H;

    ld mid, start = 0, end = 1000000000;
    for (int i = 0; i < 100; i++) {
        mid = (start + end) / 2;

        if ((ll)(L / mid) * (ll)(W / mid) * (ll)(H / mid) >= N) {
            A = mid;
            start = mid;
        }
        else {
            end = mid;
        }
    }

    cout.precision(17);
    cout << A;

    return 0;
}