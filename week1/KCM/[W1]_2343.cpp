#include <iostream>
#include <vector>
using namespace std;

class guitarLesson {
	int numberOfLesson;
	int numberOfBluray;
	vector<int> lengthOfLesson;
	int sumOfBluray = 0;
	bool isPossible(int);
public:
	guitarLesson(int n, int m) {
		numberOfLesson = n;
		numberOfBluray = m;

		lengthOfLesson.assign(n, 0);
	}
	void inputLength();
	int minOfBluray();
};

void guitarLesson::inputLength() {
	for (int i = 0; i < numberOfLesson; i++) {
		cin >> lengthOfLesson[i];
		sumOfBluray += lengthOfLesson[i];
	}
}

bool guitarLesson::isPossible(int n) {
	int sum = 0, cnt = numberOfBluray;
	bool flag = true;

	for (int i = 0; i < numberOfLesson; i++) {
		if (sum + lengthOfLesson[i] < n) {
			sum += lengthOfLesson[i];
		}
		else if (sum + lengthOfLesson[i] == n) {
			sum = 0;
			cnt--;
		}
		else {
			if (sum > n) {
				flag = false;
				break;
			}
			
			sum = lengthOfLesson[i];
			cnt--;
		}
	}

	if (sum > 0)
		cnt--;

	if (cnt < 0 || sum > n)
		flag = false;

	return flag;
}

int guitarLesson::minOfBluray() {
	int left = 1, right = sumOfBluray;
	int mid = 0;

	mid = (left + right) / 2;
	while (left < right) {
		if (isPossible(mid)) {
			right = mid;
		}
		else {
			left = mid + 1;
		}

		mid = (left + right) / 2;
	}

	return mid;
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N, M;

	cin >> N >> M;

	guitarLesson gl(N, M);

	gl.inputLength();
	cout << gl.minOfBluray();
}