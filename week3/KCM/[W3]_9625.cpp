#include <iostream>
using namespace std;

int main() {
	int A = 1, B = 0;
	int a, b;
	int n;

	cin >> n;

	for (int i = 0; i < n; i++) {
		a = B;
		b = A + B;

		A = a;
		B = b;
	}

	cout << A << ' ' << B;
}