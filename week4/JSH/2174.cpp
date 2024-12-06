#include <iostream>
#include <vector>
using namespace std;

int A, B, N, M;
string message = "OK";


int posX[101];
int posY[101];
int dirNum[101];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    
    cin >> A >> B >> N >> M;
    vector<vector<int>> grid(A + 1, vector<int>(B + 1, 0));
    
    for(int r = 1; r <= N; r++) {
        char d;
        cin >> posX[r] >> posY[r] >> d;

        if (d == 'N') {
            dirNum[r] = 0;
        }
        else if (d == 'E') {
            dirNum[r] = 1;
        }
        else if (d == 'S') {
            dirNum[r] = 2;
        }
        else {
            dirNum[r] = 3;
        }

        grid[posX[r]][posY[r]] = r;
    }
    
    bool crashed = false;
    for(int m = 0; m < M; m++) {
        int robot, repeat;
        char action;
        cin >> robot >> action >> repeat;
        if(!crashed) {
            if(action == 'L' || action == 'R') {
                repeat %= 4;
                if(action == 'L') {
                    dirNum[robot] = (dirNum[robot] + 4 - repeat) % 4;
                }
                else {
                    dirNum[robot] = (dirNum[robot] + repeat) % 4;
                }
            }
            else if(action == 'F') {
                for(int rep = 0; rep < repeat; rep++) {
                    int current_x = posX[robot];
                    int current_y = posY[robot];
                    int new_x = current_x + dx[dirNum[robot]];
                    int new_y = current_y + dy[dirNum[robot]];
                    if(new_x <= 0 || new_x > A || new_y <= 0 || new_y > B) {
                        message = "Robot " + to_string(robot) + " crashes into the wall";
                        crashed = true;
                        for(int rem = m + 1; rem < M; rem++) {
                            int temp_robot, temp_repeat;
                            char temp_action;
                            cin >> temp_robot >> temp_action >> temp_repeat;
                        }
                        break;
                    }
                    if(grid[new_x][new_y] != 0) {
                        message = "Robot " + to_string(robot) + " crashes into robot " + to_string(grid[new_x][new_y]);
                        crashed = true;
                        for(int rem = m + 1; rem < M; rem++) {
                            int temp_robot, temp_repeat;
                            char temp_action;
                            cin >> temp_robot >> temp_action >> temp_repeat;
                        }
                        break;
                    }
                    grid[current_x][current_y] = 0;
                    grid[new_x][new_y] = robot;
                    posX[robot] = new_x;
                    posY[robot] = new_y;
                }
            }
        }
    }
    cout << message << "\n";

    return 0;
}
