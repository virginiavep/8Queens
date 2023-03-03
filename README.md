# 8Queens
Program that implements a Hill-Climbing algorithm with random restarts
The 8-Queens problem requires that 8 queens be placed on a board with 8 rows and 8 columns so that no queen occupies 
the same row, column or diagonal as another queen.
The program uses a series of 0s (empty) and 1s (queen) in a grid style to represent each state as the algorithm works towards a solution.
Every state is generated with the current state’s heuristic and the number of neighboring states with lower heuristics
The action taken whether it be a restart or the generation of a neighbor state is also shown.
When a solution is reached the program displays the number of restarts and the total number of state changes.
