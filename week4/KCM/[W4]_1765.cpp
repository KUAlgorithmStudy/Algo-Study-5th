#include <iostream>
#include <vector>
using namespace std;

class teamMaking {
	vector<int> team;
	vector<bool> isVisited;
	vector<vector<pair<int, char>>> graph;
	int numOfTeam;
public:
	teamMaking(int n = 0) {
		vector<pair<int, char>> v;
		graph.assign(n + 1, v);

		numOfTeam = n;

		for (int i = 0; i <= n; i++) {
			team.push_back(i);
		}

		isVisited.assign(n + 1, false);
	}
	void inputGraph(int);
	int makeTeam();
	void dfs(int, int, char);
};

void teamMaking::inputGraph(int n) {
	char cn;
	int p, q;

	for (int i = 0; i < n; i++) {
		cin >> cn >> p >> q;

		graph[p].push_back({ q, cn });
		graph[q].push_back({ p, cn });
	}
}

int teamMaking::makeTeam() {
	int teams = numOfTeam;

	for (int i = 1; i <= teams; i++) {
		if (i == team[i]) {
			isVisited.assign(teams + 1, false);
			isVisited[i] = true;
			dfs(i, i, 'F');
		}
	}

	return numOfTeam;
}

void teamMaking::dfs(int cur, int root, char con) {
	for (int i = 0; i < graph[cur].size(); i++) {
		if (root != team[graph[cur][i].first] && graph[cur][i].second == con) {
			if (team[graph[cur][i].first] != graph[cur][i].first) {
				team[cur] = team[graph[cur][i].first];
				numOfTeam--;
			}
			else {
				team[graph[cur][i].first] = root;
				isVisited[graph[cur][i].first] = true;
				numOfTeam--;

				dfs(graph[cur][i].first, root, 'F');
			}
		}
		else if (con == 'F' && graph[cur][i].second == 'E') {
			dfs(graph[cur][i].first, root, 'E');
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, m;

	cin >> n;
	teamMaking tmk(n);

	cin >> m;
	tmk.inputGraph(m);

	cout << tmk.makeTeam();
}