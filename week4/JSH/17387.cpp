#include <iostream>
#include <algorithm>
using namespace std;


int CCW(pair<long, long> A, pair<long, long> B, pair<long, long> C) {
    long tmp = A.first * B.second + B.first * C.second + C.first * A.second;
	tmp -= B.first * A.second + C.first * B.second + A.first * C.second;

    if (tmp > 0) return 1;
    else if (tmp < 0) return -1;
    else return 0;
}

bool checkOverlap(pair<long, long> A, pair<long, long> B, pair<long, long> C, pair<long, long> D) {
    if (A > B) swap(A, B);
    if (C > D) swap(C, D);

    return A <= D && C <= B;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    pair<long, long> A, B, C, D;

    cin >> A.first >> A.second >> B.first >> B.second;
    cin >> C.first >> C.second >> D.first >> D.second;

    int check1 = CCW(A, B, C) * CCW(A, B, D);
    int check2 = CCW(C, D, A) * CCW(C, D, B);

    if (check1 == 0 && check2 == 0) {
        if (checkOverlap(A, B, C, D)) cout << 1;
        else cout << 0;
    }   
    else {
        if (check1 <= 0 && check2 <= 0) cout << 1;
        else cout << 0;
    }
    
    return 0;
}