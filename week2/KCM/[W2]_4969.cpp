#include <iostream>
#include <vector>
#define MAX_NUM 300000
using namespace std;

class MonSat {
	vector<int> monSatPrimeNumber;
	void setMonSatPrimeNumber(int);
public:
	MonSat() {
		setMonSatPrimeNumber(MAX_NUM);
	}
	void printMonSatPrimeFactor(int);
};

void MonSat::setMonSatPrimeNumber(int n) {
	int isPrime;

	monSatPrimeNumber.push_back(6);

	for (int i = 7; i < n; i++) {
		if (i % 7 == 1 || i % 7 == 6) {
			isPrime = true;

			for (int j : monSatPrimeNumber) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime)
				monSatPrimeNumber.push_back(i);
		}
	}
}

void MonSat::printMonSatPrimeFactor(int n) {
	cout << n << ':';

	for (int i = 0; i < monSatPrimeNumber.size(); i++) {
		if (monSatPrimeNumber[i] > n)
			break;

		if (n % monSatPrimeNumber[i] == 0)
			cout << ' ' << monSatPrimeNumber[i];
	}

	cout << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	MonSat ms;

	while (1) {
		cin >> n;

		if (n == 1)
			break;

		ms.printMonSatPrimeFactor(n);
	}
}