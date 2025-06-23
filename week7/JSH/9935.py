import sys

string = list(sys.stdin.readline().strip())
boom = list(sys.stdin.readline().strip())

len_boom = len(boom)
stack = []

for i in string:
    stack.append(i)
    if boom == stack[len(stack) - len_boom : len(stack)]:
        for _ in range(len_boom):
            stack.pop()

if stack:
    print(''.join(stack))
else:
    print('FRULA')
