K = int(input())
A = [1]
B = [0]

for k in range(1, K+1):
    A.append(B[k-1])
    B.append(A[k-1] + A[k])

print(A[-1], B[-1])
'''
A BA BAB BABBA BABBABAB BABBABABBABBA
1 0
0 1
1 2
2 3
3 5
5 8

A = [1]
B = [0]

A[n] = B[n-1]
B[n] = A[n-1] + B[n-1]
'''