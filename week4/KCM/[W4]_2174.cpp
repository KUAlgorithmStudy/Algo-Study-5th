#include <iostream>
#include <vector>
using namespace std;

class RobotSimulation {
	struct robot {
		int x;
		int y;
		int dir;
	};
	int w, h;
	vector<vector<int>> land;
	vector<robot> info;
public:
	void setLand(int, int);
	void inputRobot(int);
	void inputCommand(int);
};

void RobotSimulation::setLand(int x, int y) {
	w = x;
	h = y;

	vector<int> v(x + 2, -1);
	land.push_back(v);

	v.assign(x + 2, 0);
	v[0] = -1;
	v[x + 1] = -1;
	for (int i = 0; i < y; i++)
		land.push_back(v);

	v.assign(x + 2, -1);
	land.push_back(v);
}

void RobotSimulation::inputRobot(int n) {
	int x, y;
	char dir;
	robot r;

	info.assign(n + 1, r);
	
	for (int i = 1; i <= n; i++) {
		cin >> x >> y >> dir;

		land[y][x] = i;

		info[i].x = x;
		info[i].y = y;

		if (dir == 'E')
			info[i].dir = 0;
		else if (dir == 'N')
			info[i].dir = 1;
		else if (dir == 'W')
			info[i].dir = 2;
		else
			info[i].dir = 3;
	}
}

void RobotSimulation::inputCommand(int n) {
	int robotNum, rep;
	char cmd;
	int dx[4] = { 1, 0, -1, 0 };
	int dy[4] = { 0, 1, 0, -1 };
	int tx, ty;
	int curX, curY;
	int landInfo;

	for (int i = 0; i < n; i++) {
		cin >> robotNum >> cmd >> rep;

		if (cmd == 'L') {
			info[robotNum].dir = (info[robotNum].dir + rep) % 4;
		}
		else if (cmd == 'R') {
			info[robotNum].dir = (info[robotNum].dir - rep + 100) % 4;
		}
		else {
			curX = info[robotNum].x;
			curY = info[robotNum].y;

			tx = curX;
			ty = curY;

			for (int j = 0; j < rep; j++) {
				tx += dx[info[robotNum].dir];
				ty += dy[info[robotNum].dir];

				landInfo = land[ty][tx];

				if (landInfo == -1) {
					cout << "Robot " << robotNum << " crashes into the wall";
					return;
				}
				else if (landInfo != 0) {
					cout << "Robot " << robotNum << " crashes into robot " << landInfo;
					return;
				}
			}

			land[ty][tx] = robotNum;
			land[curY][curX] = 0;

			info[robotNum].x = tx;
			info[robotNum].y = ty;
		}
	}

	cout << "OK";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int A, B, N, M;
	RobotSimulation rs;

	cin >> A >> B;

	rs.setLand(A, B);
	
	cin >> N >> M;

	rs.inputRobot(N);
	rs.inputCommand(M);
}