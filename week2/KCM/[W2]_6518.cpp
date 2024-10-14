#include <iostream>
#include <vector>
#include <unordered_set>
#include <string>
using namespace std;

class Dictionary {
	unordered_set<string> unorderedDic;
	vector<string> dictionary;
	int numberOfWord;
	void setDictionary(int);
	bool isSimilar(string, string);
public:
	Dictionary(int n) {
		numberOfWord = n;
		setDictionary(n);
	}
	bool autoCorrect(string);
};

void Dictionary::setDictionary(int n) {
	string tmp;

	for (int i = 0; i < n; i++) {
		cin >> tmp;

		dictionary.push_back(tmp);
		unorderedDic.insert(tmp);
	}
}

bool Dictionary::isSimilar(string a, string b) {
	int cnt;
	string s;

	if (abs(int(a.length() - b.length())) > 1)
		return false;

	if (a.length() == b.length()) {
		cnt = 0;

		for (int i = 0; i < a.length(); i++)
			if (a[i] != b[i])
				cnt++;

		if (cnt > 2)
			return false;

		if (cnt < 2) {
			return true;
		}
		else if (cnt == 2) {
			for (int i = 0; i < a.length(); i++) {
				int n = -1;

				if (a[i] != b[i]) {
					if (n == -1) {
						n = i;
					}
					else {
						if (a[n] == b[i] && a[i] == b[n])
							return true;
					}
				}
			}
		}
	}
	else {
		if (a.length() - b.length() == 1) {
			if (a.find(b) != string::npos)
				return true;

			for (int j = 0; j < a.length(); j++) {
				s = a.substr(0, j) + a.substr(j + 1);
				if (s == b) {
					return true;
				}
			}
		}
		else if (a.length() - b.length() == -1) {
			if (b.find(a) != string::npos)
				return true;

			for (int j = 0; j < b.length(); j++) {
				s = b.substr(0, j) + b.substr(j + 1);
				if (s == a) {
					return true;
				}
			}
		}
	}

	return false;
}

bool Dictionary::autoCorrect(string s) {
	if (unorderedDic.find(s) != unorderedDic.end()) {
		cout << s << " is correct\n";

		return true;
	}

	for (string i : dictionary) {
		if (isSimilar(s, i)) {
			cout << s << " is misspelling of " << i << "\n";
			return true;
		}
	}

	cout << s << " is unknown\n";
	return false;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int numberOfWord;
	string numberToBeCorrected;


	cin >> numberOfWord;

	Dictionary dic(numberOfWord);

	cin >> numberOfWord;

	for (int i = 0; i < numberOfWord; i++) {
		cin >> numberToBeCorrected;

		dic.autoCorrect(numberToBeCorrected);
	}
}