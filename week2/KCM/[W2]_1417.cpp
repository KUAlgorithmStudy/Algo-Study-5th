#include <iostream>
#include <queue>
using namespace std;

int main() {
	int voteObtainedOfDasom;
	int numberOfCandidate;
	int tmp;
	int result = 0;
	priority_queue<int> voteObtained;
	
	cin >> numberOfCandidate;
	cin >> voteObtainedOfDasom;

	for (int i = 0; i < numberOfCandidate - 1; i++) {
		cin >> tmp;

		voteObtained.push(tmp);
	}

	if (!voteObtained.empty()) {
		while (voteObtained.top() >= voteObtainedOfDasom) {
			result++;

			tmp = voteObtained.top();
			voteObtained.pop();

			voteObtainedOfDasom++;
			tmp--;

			voteObtained.push(tmp);
		}
	}

	cout << result;
}