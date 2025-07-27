
def tree_from_trainsversals_recursive(preorder, inorder):
    if len(preorder) == 0:
        return {}
    
    v = preorder[0]
    index_v = inorder.index(v)

    new_left_inorder = inorder[:index_v]
    new_left_preorder = [ item for item in preorder if item in new_left_inorder ]
    l = tree_from_trainsversals_recursive(new_left_preorder, new_left_inorder)

    new_right_inorder = inorder[(index_v + 1):]
    new_right_preorder = [ item for item in preorder if item in new_right_inorder ]
    r = tree_from_trainsversals_recursive(new_right_preorder, new_right_inorder)

    return { "v": v, "l": l, "r": r}


def tree_from_traversals(preorder, inorder):
    len_preorder = len(preorder)
    len_inorder = len(inorder)
    if len_preorder != len_inorder:
        raise ValueError("traversals must have the same length")
    if set(preorder) != set(inorder):
        raise ValueError("traversals must have the same elements")
    if len(set(preorder)) < len(preorder) or len(set(inorder)) < len(inorder):
        raise ValueError("traversals must contain unique items")
    if len_inorder == 0:
        return {}
    
    return tree_from_trainsversals_recursive(preorder, inorder)
