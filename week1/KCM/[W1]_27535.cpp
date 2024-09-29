#include <iostream>
using namespace std;

class JejuChocolate {
	char chocolateName[5] = { 'H', 'T', 'C', 'K', 'G' };
	int numberOfChocolate[5] = { 0, 0, 0, 0, 0 };
	int total = 0;
	int prevTotal = 0;
public:
	JejuChocolate(int h, int t, int c, int k, int g) {
		numberOfChocolate[0] = h;
		numberOfChocolate[1] = t;
		numberOfChocolate[2] = c;
		numberOfChocolate[3] = k;
		numberOfChocolate[4] = g;

		total = h + t + c + k + g;
	}
	void eatChocolate(int, int, int, int, int);
	string getTotal();
	string getAlphabet();
};

void JejuChocolate::eatChocolate(int h, int t, int c, int k, int g) {
	numberOfChocolate[0] -= h;
	numberOfChocolate[1] -= t;
	numberOfChocolate[2] -= c;
	numberOfChocolate[3] -= k;
	numberOfChocolate[4] -= g;

	prevTotal = total % 10;
	total -= h + t + c + k + g;
}

string JejuChocolate::getTotal() {
	if (prevTotal == 0 || prevTotal == 1) {
		prevTotal = 10;
	}
	
	int tmp = total;
	string changedTotal = "";

	while (tmp / prevTotal > 0) {
		changedTotal = char(tmp % prevTotal + '0') + changedTotal;
		tmp = tmp / prevTotal;
	}
	changedTotal = char(tmp % prevTotal + '0') + changedTotal;

	return changedTotal;
}

string JejuChocolate::getAlphabet() {
	if (total == 0)
		return "NULL";

	string s = "";
	pair <int, char> p[5];
	pair <int, char> tmp;

	for (int i = 0; i < 5; i++)
		p[i] = { numberOfChocolate[i], chocolateName[i] };

	for (int i = 0; i < 4; i++) {
		for (int j = i + 1; j < 5; j++) {
			if (p[i].first < p[j].first || (p[i].first == p[j].first && p[i].second > p[j].second)) {
				tmp = p[i];
				p[i] = p[j];
				p[j] = tmp;
			}
		}
	}

	for (int i = 0; i < 5; i++)
		if (p[i].first > 0)
			s += p[i].second;

	return s;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int h, t, c, k, g;
	int n;

	cin >> h >> t >> c >> k >> g;

	JejuChocolate jc(h, t, c, k, g);

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> h >> t >> c >> k >> g;

		jc.eatChocolate(h, t, c, k, g);

		cout << jc.getTotal() << "7H\n";
		cout << jc.getAlphabet() << "\n";
	}
}