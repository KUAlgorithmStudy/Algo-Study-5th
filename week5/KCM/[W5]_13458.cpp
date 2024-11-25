#include <iostream>
#include <vector>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int N;
	int B, C;
	long long sum = 0;

	cin >> N;
	vector<int> examinee(N);
	
	for (int i = 0; i < N; i++)
		cin >> examinee[i];
	
	cin >> B >> C;

	sum += N;
	for (int i = 0; i < N; i++) {
		examinee[i] -= B;

		if (examinee[i] > 0)
			sum += (examinee[i] - 1) / C + 1;
	}

	cout << sum;
}