#include <iostream>
using namespace std;

int main() {
	unsigned int cnt = 1;
	int result = 9;
	int n;

	cin >> n;

	while (n > 0) {
		n -= cnt;
		result++;

		cnt = cnt << 1;
	}

	cout << result;
}