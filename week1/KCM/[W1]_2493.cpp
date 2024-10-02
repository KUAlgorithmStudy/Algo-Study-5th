#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main() {
	int numberOfTower;
	vector <int> towerHeight;
	vector <int> receiveTower;
	stack <int> s;

	cin >> numberOfTower;

	towerHeight.assign(numberOfTower + 1, 0);
	receiveTower.assign(numberOfTower + 1, 0);

	for (int i = 1; i <= numberOfTower; i++)
		cin >> towerHeight[i];

	

	for (int i = numberOfTower; i > 0; i--) {
		while (!s.empty() && towerHeight[s.top()] <= towerHeight[i]) {
			receiveTower[s.top()] = i;
			s.pop();
		}

		s.push(i);
	}

	for (int i = 1; i <= numberOfTower; i++)
		cout << receiveTower[i] << ' ';
}