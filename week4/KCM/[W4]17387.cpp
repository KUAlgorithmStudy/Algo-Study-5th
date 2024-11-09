#include <iostream>
using namespace std;

struct point {
	long long x;
	long long y;
};

int ccw(point a, point b, point c) {
	long long s = a.x * b.y + b.x * c.y + c.x * a.y;
	s -= b.x * a.y + c.x * b.y + a.x * c.y;

	if (s > 0)
		return 1;
	else if (s == 0)
		return 0;
	else
		return -1;
}

bool isCrossed(point p1, point p2, point p3, point p4) {
	int p1p2, p3p4;
	
	p1p2 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
	p3p4 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

	if (p1p2 == 0 && p3p4 == 0) {
		if (p3.x == p2.x && p1.x == p4.x)
			return p3.y <= p2.y && p1.y <= p4.y;

		return p3.x <= p2.x && p1.x <= p4.x;
	}

	return p1p2 <= 0 && p3p4 <= 0;
}

int main() {
	point p1, p2, p3, p4;
	point tmp;
	int result;

	cin >> p1.x >> p1.y >> p2.x >> p2.y;
	cin >> p3.x >> p3.y >> p4.x >> p4.y;

	if (p1.x > p2.x || (p1.x == p2.x && p1.y > p2.y)) {
		tmp = p1;
		p1 = p2;
		p2 = tmp;
	}

	if (p3.x > p4.x || (p3.x == p4.x && p3.y > p4.y)) {
		tmp = p3;
		p3 = p4;
		p4 = tmp;
	}

	result = isCrossed(p1, p2, p3, p4);

	cout << result;
}