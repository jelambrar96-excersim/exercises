class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def __str__(self):
        return f'TreeNode(data={self.data}, left={self.left}, right={self.right})'


    def generate_tree_chain(values):
        fisrt_value = values.pop(0)
        node = TreeNode(fisrt_value)
        lower_values = [ x for x in values if x <= fisrt_value ]
        if len(lower_values) > 0:
            node.left = TreeNode.generate_tree_chain(lower_values)   
        higher_values = [x for x in values if x > fisrt_value]
        if len(higher_values) > 0:
            node.right = TreeNode.generate_tree_chain(higher_values)
        return node
    
    def sort_data(self):
        out = []
        if not self.left is None:
            left_data = self.left.sort_data()
            out.extend(left_data)
        out.append(self.data)
        if not self.right is None:
            right_data = self.right.sort_data()
            out.extend(right_data)
        return out


class BinarySearchTree:
    def __init__(self, tree_data):
        self.node = TreeNode.generate_tree_chain(tree_data)

    def data(self):
        return self.node

    def sorted_data(self):
        return self.node.sort_data()
