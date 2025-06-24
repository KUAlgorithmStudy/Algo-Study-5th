#include <iostream>
#include <string>
using namespace std;

int main() {
	const string emptyStr = "FRULA";
	string s, explodeStr;
	string result;
	int explodeSize;

	cin >> s;
	cin >> explodeStr;

	explodeSize = explodeStr.size();

	for (auto chr : s) {
		result += chr;

		if (result.size() >= explodeSize && result.substr(result.size() - explodeSize, explodeSize) == explodeStr) {
			result.erase(result.size() - explodeSize, explodeSize);
		}
	}

	if (result == "")
		result = emptyStr;

	cout << result;
}