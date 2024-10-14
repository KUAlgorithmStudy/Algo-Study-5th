#include <iostream>
using namespace std;

int main() {
	long long N, L, W, H;
	long double left, right, mid;
	long double v;

	cin >> N >> L >> W >> H;

	v = (long double)L * W * H;
	
	left = 0;
	right = 1e10;

	while (left <= right) {
		mid = (left + right) / 2;

		if ((long long)(L / mid) * (long long)(W / mid) * (long long)(H / mid) >= N) {
			left = mid + 1e-10;
		}
		else {
			right = mid - 1e-10;
		}
	}
	
	mid = (left + right) / 2;

	cout << fixed;
	cout.precision(10);

	cout << mid;
}