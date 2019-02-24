
# coding: utf-8

# In[258]:


import numpy as np
import numpy.random as np_rand
import random

from tqdm import tqdm
import sys


# In[271]:


def createRandomChess(N):
    chess = np.zeros((N, N), dtype=np.int0)
    for row in chess:
        row[random.randint(0, N-1)] = 1
    return chess


# In[246]:


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


# In[265]:


def steepestAscent(chess):
    currentChess = np.array(chess)
    currentHeuristic = heuristic(currentChess)
    steps = 0

    while(True):
        currentHeuristic = heuristic(currentChess)
        tempChess = np.array(currentChess)
        tempHeuristic = currentHeuristic
        if(currentHeuristic == 0):
            break
        steps = steps + 1
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

    return (currentChess, steps)


# In[248]:


def contains(sideways, chess):
    for c in sideways:
        if(np.array_equal(c, chess)):
            return True
    return False


# In[250]:


def containsElement(arr, ele):
    for row in arr:
        exists = np.all(np.equal(row, ele))
        if(exists):
            return True
    return False


# In[278]:


def steepestAscentWithSideways(chess, sidewaysThreshold):
    currentChess = np.array(chess)
    currentHeuristic = heuristic(currentChess)

    sidewaysCounter = sidewaysThreshold
    sidewaysNext = []
    steps = 0

    while(True):
        currentHeuristic = heuristic(currentChess)
        tempChess = np.array(currentChess)
        tempHeuristic = currentHeuristic
        sideRequired = True
        if(currentHeuristic == 0):
            break
        steps = steps + 1
        for i in range(0, N):
            row = currentChess[i]
            currentPosition = np.where(row)[0][0]
            for pos in [p for p in range(0, N) if p != currentPosition]:
                nextChess = np.array(currentChess)
                nextChess[i][currentPosition] = 0
                nextChess[i][pos] = 1
                nextHeuristic = heuristic(nextChess)
                if(nextHeuristic == 0 or nextHeuristic < tempHeuristic):
                    sideRequired = False
                    tempChess = np.array(nextChess)
                    tempHeuristic = nextHeuristic
                if(sideRequired and (nextHeuristic == tempHeuristic) and (not containsElement(sidewaysNext, nextChess))):
                    tempChess = np.array(nextChess)
                    tempHeuristic = nextHeuristic
                    sidewaysNext.append(nextChess)
        if(tempHeuristic == 0):
            currentChess = np.array(tempChess)
            break
        if(tempHeuristic == currentHeuristic):
            if(sidewaysCounter == 0 or len(sidewaysNext) == 0):
                break
            else:
                if(sidewaysCounter > 0):
                    sidewaysCounter = sidewaysCounter - 1
                currentChess = sidewaysNext.pop(np.random.randint(0, len(sidewaysNext)))
                sidewaysNext = []
        elif(tempHeuristic < currentHeuristic):
            sidewaysCounter = sidewaysThreshold
            sidewaysNext = []
            currentChess = np.array(tempChess)

    return (currentChess, steps)


# In[279]:


def randomRestartWithoutSideways(chess):
    currentHeuristic = sys.maxsize
    solvedChess = chess
    steps = 0
    restartCount = -1
    while(currentHeuristic != 0):
        (solvedChess, steps) = steepestAscent(chess)
        currentHeuristic = heuristic(solvedChess)
        chess = createRandomChess(chess.shape[0])
        restartCount = restartCount + 1
    return (solvedChess, steps, restartCount)


# In[280]:


def randomRestartWithSideways(chess, threshold):
    currentHeuristic = sys.maxsize
    solvedChess = chess
    steps = 0
    restartCount = -1
    while(currentHeuristic != 0):
        (solvedChess, steps) = steepestAscentWithSideways(chess, threshold)
        currentHeuristic = heuristic(solvedChess)
        chess = createRandomChess(chess.shape[0])
        restartCount = restartCount + 1
    return (solvedChess, steps, restartCount)


# In[283]:

print()
N = int(input("Enter number of queens: "))
print()

# In[285]:

count = 500

# Steepest Ascent Hill 
success = 0
failure = 0
successSteps = 0
failureSteps = 0
print("Steepest Ascent...... simulation: -")
for sample in tqdm(range(0, count)):
    chess = np.zeros((N, N), dtype=np.int0)
    for row in chess:
        row[random.randint(0, N-1)] = 1
#     print("Start", chess)
    (solvedChess, steps) = steepestAscent(chess)
    if(heuristic(solvedChess) == 0):
        success = success + 1
        successSteps = successSteps + steps
    else:
        failure = failure + 1
        failureSteps = failureSteps + steps

if(success > 0):
    print("Success states: ", success)
    print("Success percentage: ", success/5, "%")
    print("Average success steps: ", successSteps//(success))
else:
    print("No Success")
if(failure > 0):
    print("Failure states: ", failure)
    print("Failure percentage: ", failure/5, "%")
    print("Average failure steps: ", failureSteps//(failure))
else:
    print("No Failure")
print()


# Steepest Ascent Hill Climbing with sideways moves allowed
success = 0
failure = 0
successSteps = 0
failureSteps = 0
print("Steepest Ascent with sideways moves allowed...... simulation: -")
for sample in tqdm(range(0, count)):
    chess = np.zeros((N, N), dtype=np.int0)
    for row in chess:
        row[random.randint(0, N-1)] = 1
#     print("Start", chess)
    (solvedChess, steps) = steepestAscentWithSideways(chess, 100)
    if(heuristic(solvedChess) == 0):
        success = success + 1
        successSteps = successSteps + steps
    else:
        failure = failure + 1
        failureSteps = failureSteps + steps

if(success > 0):
    print("Success states: ", success)
    print("Success percentage: ", success/5, "%")
    print("Average success steps: ", successSteps//(success))
else:
    print("No Success")
if(failure > 0):
    print("Failure states: ", failure)
    print("Failure percentage: ", failure/5, "%")
    print("Average failure steps: ", failureSteps//(failure))
else:
    print("No Failure")
print()


# Steepest Ascent Hill Climbing random restart without sideways moves
success = 0
failure = 0
successSteps = 0
failureSteps = 0
print("Random Restart without sideways moves...... simulation: -")
for sample in tqdm(range(0, count)):
    chess = np.zeros((N, N), dtype=np.int0)
    for row in chess:
        row[random.randint(0, N-1)] = 1
    (solvedChess, steps, restartCount) = randomRestartWithoutSideways(chess)
    if(heuristic(solvedChess) == 0):
        success = success + 1
        successSteps = successSteps + steps
    else:
        failure = failure + 1
        failureSteps = failureSteps + steps

if(success > 0):
    print("Average success steps: ", successSteps//(success))
else:
    print("No Success")

print("Average number of restarts required: ", restartCount)
print()


# Steepest Ascent Hill Climbing random restart with sideways moves allowed
success = 0
failure = 0
successSteps = 0
failureSteps = 0
print("Random Restart with sideways moves allowed...... simulation: -")
for sample in tqdm(range(0, count)):
    chess = np.zeros((N, N), dtype=np.int0)
    for row in chess:
        row[random.randint(0, N-1)] = 1
#     print("Start", chess)
    (solvedChess, steps, restartCount) = randomRestartWithSideways(chess, 100)
    if(heuristic(solvedChess) == 0):
        success = success + 1
        successSteps = successSteps + steps
    else:
        failure = failure + 1
        failureSteps = failureSteps + steps

if(success > 0):
    print("Average success steps: ", successSteps//(success))
else:
    print("No Success")

print("Average number of restarts required: ", restartCount)

