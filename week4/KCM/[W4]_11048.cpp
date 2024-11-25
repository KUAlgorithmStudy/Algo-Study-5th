#include <iostream>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

vector<vector<int>> candyNum;
vector<vector<bool>> isVisited;

int candyMax(int h, int w) {
	int x, y, len;
	queue<pair<int, int>> q;
	
	q.push({ 0, 0 });
	isVisited[0][0] = true;

	while (!q.empty()) {
		len = q.size();

		for (int i = 0; i < len; i++) {
			x = q.front().second;
			y = q.front().first;
			q.pop();

			if (x + 1 < w && !isVisited[y][x + 1]) {
				if (y == 0) {
					candyNum[y][x + 1] += candyNum[y][x];
				}
				else {
					candyNum[y][x + 1] += max(candyNum[y][x], candyNum[y - 1][x + 1]);
				}

				q.push({ y, x + 1 });
				isVisited[y][x + 1] = true;
			}

			if (y + 1 < h && !isVisited[y + 1][x]) {
				if (x == 0) {
					candyNum[y + 1][x] += candyNum[y][x];
				}
				else {
					candyNum[y + 1][x] += max(candyNum[y][x], candyNum[y + 1][x - 1]);
				}

				q.push({ y + 1, x });
				isVisited[y + 1][x] = true;
			}
		}
	}

	return candyNum[h - 1][w - 1];
}

int main() {
	int n, m;

	cin >> n >> m;

	vector<int> v(m, 0);
	candyNum.assign(n, v);

	vector<bool> v2(m, false);
	isVisited.assign(n, v2);

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> candyNum[i][j];

	cout << candyMax(n, m);
}