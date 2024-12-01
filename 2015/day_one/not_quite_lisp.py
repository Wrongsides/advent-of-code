import os


def readDirections(directions: str):
    if directions == "":
        return "()"
    if not directions.startswith("(") and not directions.startswith(")"):
        file = open(os.getcwd() + "/" + directions, "r")
        return file.read().strip()
    return directions

def moveSanta(directions: str):
    floor = 0
    for instruction in readDirections(directions):
        floor += 1 if instruction == "(" else -1
    return floor

def entersBasementAt(directions: str):
    count = 0
    floor = 0
    for instruction in readDirections(directions):
        floor += 1 if instruction == "(" else -1
        count += 1
        if floor == -1:
            return count
    return 0

