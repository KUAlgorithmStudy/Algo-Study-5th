#include <iostream>
#include <cstring>
using namespace std;

int n, m, a, b, cmd;
int parent[1000002];

int find(int node) {
    if (node == parent[node]) return node;
    return parent[node] = find(parent[node]);
}

void merge(int node1, int node2) {
    int parentNode1 = find(node1);
    int parentNode2 = find(node2);

    if(parentNode1 == parentNode2) return;
    
    parent[parentNode1] = parentNode2;
    return;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;

    for(int i=0; i <= n; i++) parent[i] = i;

    for(int i=0; i < m; i++) {
        cin >> cmd >> a >> b;

        if(cmd == 0) {
            merge(a, b);
        }
        else {
            if (find(a) == find(b)) cout << "YES\n";
            else cout << "NO\n";
        }
    }

    return 0;
}