#include <iostream>
using namespace std;

int N, M, ans;
int parent[500001];

// MakeSet function
void makeSet(int N) {
	for(int i=0; i < N; i++) {
			parent[i] = i;
	}
}

// Advanced Find function
int find(int node) {
	if (node == parent[node]) {
			return node;
	}
	
	parent[node] = find(parent[node]);
	
	return parent[node];
}

// Union function
bool merge(int node1, int node2) {
	int parentNode1 = find(node1);
	int parentNode2 = find(node2);
	
	if (parentNode1 == parentNode2) return true;
	
	parent[parentNode1] = parentNode2;
	return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> M;
    makeSet(N);

    int u, v;
    for (int i = 1; i <= M; i++) {
        cin >> u >> v;
        if (merge(u, v)) {
            ans = i;
            break;
        }
    }

    if (ans == 0) cout << 0;
    else cout << ans;

    return 0;
}