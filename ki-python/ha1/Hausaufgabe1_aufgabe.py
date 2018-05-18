import collections
import heapq
import math
import sys

class Queue:
    def __init__(self):
        self.elements = collections.deque()
    
    def empty(self):
        return len(self.elements) == 0
    
    def put(self, x):
        self.elements.append(x)
    
    def get(self):
        return self.elements.popleft()

class Stack:
    def __init__(self):
        self.elements = []
    
    def empty(self):
        return len(self.elements) == 0
    
    def put(self, x):
        self.elements.append(x)
    
    def get(self):
        return self.elements.pop()


class PriorityQueue:
    def __init__(self):
        self.elements = []
    
    def empty(self):
        return len(self.elements) == 0
    
    def put(self, item, priority):
        heapq.heappush(self.elements, (priority, item))
    
    def get(self):
        return heapq.heappop(self.elements)[1]


class SimpleGraph:
    def __init__(self,edges):
        self.edges = edges
    
    def neighbors(self, id):
        return self.edges[id]


def heuristic(a, b):
    ## S Added
    # b)
    return hrst[b]
    ## E Added

def a_star_search(graph, start, goal):
    frontier = PriorityQueue()
    frontier.put(start, 0)
    came_from = {}
    cost_so_far = {}
    came_from[start] = None
    cost_so_far[start] = 0
    
    while not frontier.empty():
        current = frontier.get()
        print("Visiting: ",end="")
        print(current,end="")
        print(" with cost: ",end="")
        print(cost_so_far[current])
        if current == goal:
            print("Goal found: ",end="")
            print(goal)
            break
        
        #calculate new cost for each neighbor
        nn = graph.neighbors(current)
        for nextkey in nn.keys():
            nextcost = nn[nextkey]
            new_cost = cost_so_far[current] + nextcost
            if nextkey not in cost_so_far or new_cost < cost_so_far[nextkey]:
                cost_so_far[nextkey] = new_cost
                #notice the change in the call to the heuristic function in the next line:
                priority = new_cost + heuristic(goal, nextkey)
                frontier.put(nextkey, priority)
                came_from[nextkey] = current
   
    return came_from, cost_so_far


def greedy_search(graph, start, goal):
    ## S Added
    #c)
    frontier = Stack()
    frontier.put(start)
    came_from = {}
    came_from[start] = None

    while not frontier.empty():
        current = frontier.get()
        print("Visiting: ",end="")
        print(current)
        if current == goal:
            print("Goal found: ",end="")
            print(goal)
            break
        
        # Wähle den nächsten Nachbarn mit der geringsten Heuristik
        lowestNeighbour = current
        for neighbour in graph.neighbors(current):
            if hrst[lowestNeighbour] > hrst[neighbour]:
                lowestNeighbour = neighbour
        came_from[lowestNeighbour] = current
        frontier.put(lowestNeighbour)

    return came_from
    ## E Added


class LabelledGraph:
    def __init__(self,edges):
        self.edges = edges
    
    def neighbors(self, id):
        return self.edges[id]

## S Added

# a)
# Alle Kanten als dict definiert mit ID:Kosten
nodeS = {1:85, 2:217, 7:173}
node1 = {0:85, 4:80}
node2 = {0:217, 5:186, 6:103}
node3 = {6:183}
node4 = {1:80, 8:250}
node5 = {2:186}
node6 = {2:103, 3:183, 9:167}
node7 = {0:173, 9:502}
node8 = {4:250, 9:84}
nodeZ = {6:167, 7:502, 8:84}
# Graphen als SimpleGraph mit edges als dict von dicts ID:{ID:Kosten}
graph = LabelledGraph({0:nodeS, 1:node1, 2:node2, 3:node3, 4:node4, 5:node5, 6:node6, 7:node7, 8:node8, 9:nodeZ})

# b)
# Heuristik als dict
hrst = {0:304, 1:272, 2:219, 3:189, 4:253, 5:318, 6:150, 7:383, 8:57, 9:0}

# d)
# Main Funktion sowie Ausgabefunktion für den Pfad vom Start zum Ziel

start = 0
goal = 9

def printPath(a, b, came_from):
    # gebe den pfad durch Rekursion über came_from aus
    if a != b:
        printPath(a,came_from[b],came_from)
        print(" -> {}".format(b), end="")
    else:
        print(a,end="")
    return

def main(args):
    inString = None
    try:
        inString = input("Bitte den Suchalgorithmus angeben (greedy/a*): ")
    except EOFError as e:
        print(e)
        return 1
    if inString is not None:
        inString = inString.lower().rstrip()
        if inString in ["g","gr","gre","gree","greed","greedy"]:
            came_from = greedy_search(graph, start, goal)
        elif inString in ["a*", "a", "as", "ast", "asta", "astar"]:
            came_from = a_star_search(graph, start, goal)[0]
        print("Pfad vom Start {} zum Ziel {}: ".format(start,goal), end="")
        printPath(start,goal,came_from)
    return 0

if __name__ == "__main__":
    main(sys.argv)

## E Added