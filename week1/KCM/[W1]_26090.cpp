#include <iostream>
#include <vector>
using namespace std;

class CompleteArray {
	vector<bool> isPrime;
	vector<int> primeNumber;
	vector<int> numbers;
	int maxNumber;
	int lengthOfArray;
	void inputNumbers();
	void calculatePrime();
public:
	CompleteArray(int n = 2000) {
		numbers.assign(n, 0);
		lengthOfArray = n;
	}
	int calculateStability();
};

void CompleteArray::inputNumbers() {
	int max = 0;
	int len = numbers.size();

	for (int i = 0; i < len; i++) {
		cin >> numbers[i];
		
		if (numbers[i] > max)
			max = numbers[i];
	}

	maxNumber = max;
}

void CompleteArray::calculatePrime() {
	int maxPrime = maxNumber * lengthOfArray;

	isPrime.assign(maxPrime + 1, true);

	isPrime[0] = false;
	isPrime[1] = false;

	for (int i = 2; i < maxPrime + 1; i++) {
		if (isPrime[i]) {
			primeNumber.push_back(i);

			int j = i * 2;
			while (j <= maxPrime) {
				isPrime[j] = false;
				j += i;
			}
		}
	}
}

int CompleteArray::calculateStability() {
	inputNumbers();
	calculatePrime();

	int stability = 0;
	int sum;

	for (int i : primeNumber) {
		if (i > lengthOfArray)
			break;

		sum = 0;

		for (int j = 0; j < i; j++)
			sum += numbers[j];

		if (isPrime[sum])
			stability++;

		for (int j = i; j < lengthOfArray; j++) {
			sum = sum - numbers[j - i] + numbers[j];

			if (isPrime[sum])
				stability++;
		}
	}

	return stability;
}

int main() {
	int n;

	cin >> n;
	
	CompleteArray ca(n);

	cout << ca.calculateStability();
}