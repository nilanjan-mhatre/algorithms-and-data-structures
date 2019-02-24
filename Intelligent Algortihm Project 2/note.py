import numpy as np
import random

from tqdm import tqdm_notebook as tqdm

def heuristic(chess):
    (x,y) = np.where(chess)
    queens = list(zip(x,y))
    totalAttackingPairs = 0
    for i in range(0, len(queens) - 1):
        (x1, y1)= queens[i]
        for j in range(i+1, len(queens)):
            (x2, y2)= queens[j]
            if(x1 == x2 or y1 == y2):
                totalAttackingPairs = totalAttackingPairs + 1
            elif(abs(x1 - x2) == abs(y1 - y2)):
                totalAttackingPairs = totalAttackingPairs + 1
    return totalAttackingPairs

def steepestAscent(chess):
    currentChess = np.array(chess)
    currentHeuristic = heuristic(currentChess)

    while(True):
        currentHeuristic = heuristic(currentChess)
        tempChess = np.array(currentChess)
        tempHeuristic = heuristic(tempChess)
        for i in range(0, N):
            row = currentChess[i]
            currentPosition = np.where(row)
            for pos in [p for p in range(0, N) if p != currentPosition]:
                nextChess = np.array(currentChess)
                nextChess[i][currentPosition] = 0
                nextChess[i][pos] = 1
                nextHeuristic = heuristic(nextChess)
                if(nextHeuristic < tempHeuristic):
                    tempChess = np.array(nextChess)
                    tempHeuristic = nextHeuristic
        if(tempHeuristic >= currentHeuristic):
            break
        currentChess = np.array(tempChess)

    return currentChess

def steepestAscentWithSideways(chess):
    currentChess = np.array(chess)
    currentHeuristic = heuristic(currentChess)

    sidewaysThreshold = 100
    sidewaysVisited = np.array([])

    while(True):
        currentHeuristic = heuristic(currentChess)
        tempChess = np.array(currentChess)
        tempHeuristic = heuristic(tempChess)
        for i in range(0, N):
            row = currentChess[i]
            currentPosition = np.where(row)
            for pos in [p for p in range(0, N) if p != currentPosition]:
                nextChess = np.array(currentChess)
                nextChess[i][currentPosition] = 0
                nextChess[i][pos] = 1
                if(not contains(sidewaysVisited, nextChess)):
                    nextHeuristic = heuristic(nextChess)
                    if(nextHeuristic <= tempHeuristic):
                        tempChess = np.array(nextChess)
                        tempHeuristic = nextHeuristic
        if(tempHeuristic > currentHeuristic or np.array_equal(tempChess, currentChess)):
            break
        if(tempHeuristic == currentHeuristic):
            sidewaysThreshold = sidewaysThreshold - 1
            np.append(sidewaysVisited, currentChess)
        else:
            sidewaysThreshold = 100
            sidewaysVisited = np.array([])
        if(sidewaysThreshold == 0):
            break
        currentChess = np.array(tempChess)

    return currentChess
    
count = 500
success = 0
for sample in tqdm(range(0, count)):
    chess = np.zeros((N, N), dtype=np.int0)
    for row in chess:
        row[random.randint(0, N-1)] = 1
    solvedChess = steepestAscent(chess)
    if(heuristic(solvedChess) == 0):
        success = success + 1
    #print(solvedChess, heuristic(chess), heuristic(solvedChess))
print(success)