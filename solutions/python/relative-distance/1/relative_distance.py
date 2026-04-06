"""
# Relative Distance
determine the degree of separation between two individuals in a family tree. 
"""

from collections import defaultdict, deque


class RelativeDistance:
    """
    Compute the degree of separation between individuals in a family tree.

    The family tree is represented as an undirected graph where:
    - Parent-child relationships have a distance of 1
    - Sibling relationships have a distance of 1

    The class builds this graph and allows querying the shortest distance
    (degree of separation) between any two individuals using BFS.
    """

    _family_graph = defaultdict(set)

    def __init__(self, family_tree):
        """
        Initialize the family graph from a given family tree structure.

        Args:
            family_tree (dict):
                A dictionary mapping each parent (str) to a list of their children (list[str]).

        Behavior:
            - Adds bidirectional edges between parents and children.
            - Adds bidirectional edges between siblings.
        """
        for parent, children in family_tree.items():
            for i, child in enumerate(children):
                self._family_graph[parent].add(child)
                self._family_graph[child].add(parent)

                # Connect siblings
                for child_j in children[i + 1:]:
                    self._family_graph[child].add(child_j)
                    self._family_graph[child_j].add(child)

    def degree_of_separation(self, person_a, person_b):
        """
        Calculate the minimum degree of separation between two individuals.

        This uses Breadth-First Search (BFS) to find the shortest path
        between the two nodes in the graph.

        Args:
            person_a (str): The starting individual.
            person_b (str): The target individual.

        Returns:
            int: The number of edges in the shortest path between person_a and person_b.

        Raises:
            ValueError:
                - If person_a is not in the family tree.
                - If person_b is not in the family tree.
                - If there is no connection between the two individuals.
        """
        if person_a not in self._family_graph:
            raise ValueError("Person A not in family tree.")
        if person_b not in self._family_graph:
            raise ValueError("Person B not in family tree.")

        visited = set()
        queue = deque([(person_a, 0)])

        while queue:
            current, dist = queue.popleft()

            if current == person_b:
                return dist

            if current in visited:
                continue

            visited.add(current)

            for neighbor in self._family_graph[current]:
                if neighbor not in visited:
                    queue.append((neighbor, dist + 1))

        raise ValueError("No connection between person A and person B.")
