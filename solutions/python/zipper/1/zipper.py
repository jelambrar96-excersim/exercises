class Zipper:

    def __init__(self, value, left_node=None, right_node=None, parent_node=None):
        self._value = value
        self.left_node = left_node
        self.right_node = right_node
        self.parent_node = parent_node
    
    @staticmethod
    def from_tree(tree):

        value = tree.get("value", None)
        root = Zipper(value)
        
        left = tree.get("left", None)
        if not left is None:
            left = Zipper.from_tree(left)
            left.parent_node = root
            root.left_node = left
        
        right = tree.get("right", None)
        if not right is None:
            right = Zipper.from_tree(right)
            right.parent_node = root
            root.right_node = right

        return root

    def value(self):
        return self._value

    def set_value(self, value):
        self._value = value
        return self

    def left(self):
        return self.left_node

    def set_left(self, tree):
        new_tree = None
        if tree is not None:
            new_tree = Zipper.from_tree(tree)
            new_tree.parent_node = self
        if self.left_node is None:
            self.left_node = new_tree
            return self
        old_left_node = self.left_node
        del old_left_node
        self.left_node = new_tree
        return self

    def right(self):
        return self.right_node

    def set_right(self, tree):
        new_tree = None
        if tree is not None:
            new_tree = Zipper.from_tree(tree)
            new_tree.parent_node = self
        if self.right_node is None:
            self.right_node = new_tree
            return self
        old_right_node = self.right_node
        del old_right_node
        self.right_node = new_tree
        return self

    def up(self):
        return self.parent_node

    def to_tree(self):
        root = self
        while root.up() is not None:
            root = root.up()
        return root.to_tree_aux()


    def to_tree_aux(self):
        return {
            "value": self._value,
            "left":  None if self.left_node  is None else self.left_node.to_tree_aux(), 
            "right": None if self.right_node is None else self.right_node.to_tree_aux(),
        }


if __name__ == '__main__':

    initial = {
        "value": 1,
        "left": {
            "value": 2,
            "left": None,
            "right": {"value": 3, "left": None, "right": None},
        },
        "right": {"value": 4, "left": None, "right": None},
    }

    expected = {
        "value": 1,
        "left": {
            "value": 2,
            "left": None,
            "right": {"value": 3, "left": None, "right": None},
        },
        "right": {"value": 4, "left": None, "right": None},
    }

    zipper = Zipper.from_tree(initial)
    result = zipper.left().right().to_tree()

    print(result)