// https://sdev.tistory.com/1545
#include <iostream>
using namespace std;

int w, h, n, dir, dist;
int ans, store[101];

int move(int dir, int dist) {
    if (dir == 1) return h + dist;
    if (dir == 2) return 2 * (w + h) - dist;
    if (dir == 3) return h - dist;
    return w + h + dist;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> w >> h;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> dir >> dist;
        store[i] = move(dir, dist);
    }

    cin >> dir >> dist;
    dist = move(dir, dist);
    w = 2 * (w + h);

    for (int i = 0; i < n; i++) {
        dir = (store[i] - dist + w) % w;
        if (dir * 2 > w) dir = w - dir;
        ans += dir;
    }

    cout << ans;

    return 0;
}