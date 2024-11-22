#include <iostream>
using namespace std;

int main() {
	int w, h;
	int n;
	int donguen[2];
	int store[100][2];
	int sum = 0;

	cin >> w >> h;
	cin >> n;

	for (int i = 0; i < n; i++)
		cin >> store[i][0] >> store[i][1];

	cin >> donguen[0] >> donguen[1];

	for (int i = 0; i < n; i++) {
		switch (donguen[0]) {
		case 1:
			switch (store[i][0]) {
			case 1:
				if (store[i][1] >= donguen[1]) {
					sum += store[i][1] - donguen[1];
				}
				else {
					sum += donguen[1] - store[i][1];
				}
				break;
			case 2:
				sum += h;

				if (store[i][1] + donguen[1] <= w - store[i][1] + w - donguen[1]) {
					sum += store[i][1] + donguen[1];
				}
				else {
					sum += w - store[i][1] + w - donguen[1];
				}
				break;
			case 3:
				sum += donguen[1] + store[i][1];
				break;
			case 4:
				sum += w - donguen[1] + store[i][1];
				break;
			}
			break;
		case 2:
			switch (store[i][0]) {
			case 1:
				sum += h;

				if (store[i][1] + donguen[1] <= w - store[i][1] + w - donguen[1]) {
					sum += store[i][1] + donguen[1];
				}
				else {
					sum += w - store[i][1] + w - donguen[1];
				}
				break;
			case 2:
				if (store[i][1] >= donguen[1]) {
					sum += store[i][1] - donguen[1];
				}
				else {
					sum += donguen[1] - store[i][1];
				}
				break;
			case 3:
				sum += donguen[1] + h - store[i][1];
				break;
			case 4:
				sum += w - donguen[1] + h - store[i][1];
				break;
			}
			break;
		case 3:
			switch (store[i][0]) {
			case 1:
				sum += donguen[1] + store[i][1];
				break;
			case 2:
				sum += h - donguen[1] + store[i][1];
				break;
			case 3:
				if (store[i][1] >= donguen[1]) {
					sum += store[i][1] - donguen[1];
				}
				else {
					sum += donguen[1] - store[i][1];
				}
				break;
			case 4:
				sum += w;

				if (store[i][1] + donguen[1] <= h - store[i][1] + h - donguen[1]) {
					sum += store[i][1] + donguen[1];
				}
				else {
					sum += h - store[i][1] + h - donguen[1];
				}
				break;
			}
			break;
		case 4:
			switch (store[i][0]) {
			case 1:
				sum += donguen[1] + w - store[i][1];
				break;
			case 2:
				sum += h - donguen[1] + w - store[i][1];
				break;
			case 3:
				sum += w;

				if (store[i][1] + donguen[1] <= h - store[i][1] + h - donguen[1]) {
					sum += store[i][1] + donguen[1];
				}
				else {
					sum += h - store[i][1] + h - donguen[1];
				}
				break;
			case 4:
				if (store[i][1] >= donguen[1]) {
					sum += store[i][1] - donguen[1];
				}
				else {
					sum += donguen[1] - store[i][1];
				}
				break;
			}
			break;
		}
	}

	cout << sum;
}