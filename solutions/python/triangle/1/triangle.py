def is_triangle(sides):
    if len(sides) != 3:
        return False
    if sides[0] < 0 or sides[1] < 0 or sides[2] < 0:
        return False
    if sum(sides) == 0:
        return False
    sorted_sides = sorted(sides)
    if sorted_sides[2] > (sorted_sides[0] + sorted_sides[1]):
        return False
    return True


def equilateral(sides):
    if not is_triangle(sides):
        return False
    return sides[0] == sides[1] and sides[0] == sides[2] and sides[1] == sides[2]


def isosceles(sides):
    if not is_triangle(sides):
        return False
    return sides[0] == sides[1] or sides[0] == sides[2] or sides[1] == sides[2]



def scalene(sides):
    if not is_triangle(sides):
        return False
    return sides[0] != sides[1] and sides[0] != sides[2] and sides[1] != sides[2]

