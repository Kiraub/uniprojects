#!/usr/bin/python3
import collections
import heapq
import math

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




def tree_search_1(queue,graph, start):
    # return "came_from"
    frontier = queue
    frontier.put(start)
    came_from = {}
    came_from[start] = None
    
    while not frontier.empty():
        current = frontier.get()
        print("visiting: ",end="")
        print(current)
        for next in graph.neighbors(current):
            if next not in came_from:
                frontier.put(next)
                came_from[next] = current
    
    return came_from



def breadth_first_search_1(graph,start):
    q = Queue()
    tree_search_1(q,example_graph,'A')
    return #TODO
def depth_first_search_1(graph,start):
    return #TODO

example_graph = SimpleGraph({
    'A': {'B'},
    'B': {'A','C','D'},
    'C': {'A'},
    'D': {'A','E'},
    'E': {'B'}
})


breadth_first_search_1(example_graph, 'A')
depth_first_search_1(example_graph, 'A')


def tree_search_2(queue,graph, start, goal):
    return #TODO


def breadth_first_search_2(graph,start,goal):
    return #TODO
def depth_first_search_2(graph,start,goal):
    return #TODO




breadth_first_search_2(example_graph, 'A', 'E')
depth_first_search_2(example_graph, 'A', 'E')


#___________________________________________________
#A* search

def heuristic(a, b):
    return #TODO


def a_star_search(graph, start, goal):
    return None, None



class LabelledGraph:
    def __init__(self, wedges):
        self.wedges = wedges


romania_map = LabelledGraph(dict(
        Oradea=dict(Zerind=71, Sibiu=151),
        Zerind=dict(Arad=75,Oradea=71),
        Sibiu =dict(Arad=140,Oradea=151,Fagaras=99),
        Fagaras=dict(Sibiu=99),
        Arad=dict(Zerind=75, Sibiu=140, Timisoara=118)))

romania_map.locations = dict(
    Arad=(91, 492),Fagaras=(305, 449),Oradea=(131, 571),
    Sibiu=(207, 457), Zerind=(108, 531))


came_from, cost_so_far = a_star_search(romania_map, 'Oradea','Fagaras')
