from json import dumps
from itertools import pairwise

def find_value(tree, value, parents=[]):
    # print(tree.label, parents)
    if tree.label == value:
        return [*parents, tree]
    for ch in tree.children:
        res = find_value(ch, value, [*parents, tree])
        # print(res)
        if res is not None:
            return res
    return None


class Tree:
    def __init__(self, label, children=None):
        self.label = label
        self.children = children if children is not None else []

    def __dict__(self):
        return {self.label: [c.__dict__() for c in sorted(self.children)]}

    def __str__(self, indent=None):
        return dumps(self.__dict__(), indent=indent)

    def __lt__(self, other):
        return self.label < other.label

    def __eq__(self, other):
        return self.__dict__() == other.__dict__()

    def from_pov(self, from_node):

        path_from_node = find_value(self, from_node)
        if path_from_node is None:
            raise ValueError("Tree could not be reoriented")
        
        for i0, i1 in pairwise(range(len(path_from_node))):
            path_from_node[i0].children = [ ch for ch in path_from_node[i0].children if ch != path_from_node[i1] ]
            path_from_node[i1].children = sorted([*path_from_node[i1].children, path_from_node[i0]])
        
        return path_from_node[-1]

    def path_to(self, from_node, to_node):

        path_from_node = find_value(self, from_node)
        if path_from_node is None:
            raise ValueError("Tree could not be reoriented")
        path_from_node =  [ item.label for item in path_from_node ]
        
        path_to_node = find_value(self, to_node)
        if path_to_node is None:
            raise ValueError("No path found")
        path_to_node = [ item.label for item in path_to_node]

        last_common_element = [item for item in path_from_node if item in path_to_node][-1]
        path_from_node_2 = [ item for item in path_from_node[::-1] if item not in path_to_node]
        path_to_node_2 = [ item for item in path_to_node if item not in path_from_node]

        return [*path_from_node_2, last_common_element, *path_to_node_2]

