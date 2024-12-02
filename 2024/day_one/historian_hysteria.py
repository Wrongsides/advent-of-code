import os
from collections import Counter


def parseInput(filename: str):
    list_one: list[int] = []
    list_two: list[int] = []
    file = open(os.getcwd() + "/" + filename, "r")
    for line in file:
        locations = line.split()
        list_one.append(int(locations[0]))
        list_two.append(int(locations[1]))
    return {"list_one": list_one, "list_two": list_two}

def reconsile(list_one: list[int], list_two: list[int]):
    list_one.sort()
    list_two.sort()
    total = 0
    while len(list_one) > 0:
        total += abs(list_one.pop() - list_two.pop())
    return total

def similarity(list_one: list[int], list_two: list[int]):
    total: int = 0
    counts: dict[int, int] = Counter(list_two)
    while len(list_one) > 0:
        location = list_one.pop()
        similarity = location * counts.get(location, 0)
        total += similarity
    return total

def similarityFile(filename: str):
    lists = parseInput(filename)
    return similarity(lists.get('list_one', []), lists.get('list_two', []))

def reconsileFile(filename: str):
    lists = parseInput(filename)
    return reconsile(lists.get('list_one', []), lists.get('list_two', []))

