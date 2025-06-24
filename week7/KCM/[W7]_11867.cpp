#include <iostream>
using namespace std;

int main() {
	int N, M;

	cin >> N >> M;

	if (N % 2 == 0 || M % 2 == 0) {
		cout << 'A';
	}
	else {
		cout << 'B';
	}
}