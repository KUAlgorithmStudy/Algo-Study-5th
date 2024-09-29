#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N, height;
stack<pair<int, int>> tower;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;
    tower.push({100000001, 0});

    for(int i=1; i <= N; i++) {
        cin >> height;

        while(tower.top().first < height) {
            tower.pop();
        }

        cout << tower.top().second << " ";
        tower.push({height, i});
    }

    return 0;
}