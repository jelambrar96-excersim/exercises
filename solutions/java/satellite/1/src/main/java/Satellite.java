import java.util.List;
import java.util.Set;

public class Satellite {


    private Node nodeFromTransversals(List<Character> preorderInput, List<Character> inorderInput) {

        int sizeInorder = inorderInput.size();
        if (sizeInorder == 0) {
            return null;
        }

        Character value = preorderInput.get(0);
        int indexValue = inorderInput.indexOf(value);

        List<Character> leftInorder = inorderInput.subList(0, indexValue);
        List<Character> leftPreorder = preorderInput.stream().filter(leftInorder::contains).toList();
        Node leftSubtree = nodeFromTransversals(leftPreorder, leftInorder);

        List<Character> rightInorder = inorderInput.subList(indexValue + 1, sizeInorder);
        List<Character> rightPreorder = preorderInput.stream().filter(rightInorder::contains).toList();
        Node rightSubtree = nodeFromTransversals(rightPreorder, rightInorder);

        Node root = new Node(value);
        root.left = leftSubtree;
        root.right = rightSubtree; 

        return root;
    }

    public Tree treeFromTraversals(List<Character> preorderInput, List<Character> inorderInput) {
        
        int sizePreorder = preorderInput.size();
        int sizeInorder = inorderInput.size();

        if (sizePreorder != sizeInorder) {
            throw new IllegalArgumentException("traversals must have the same length");
        }

        Set<Character> preorderSet = Set.copyOf(preorderInput);
        Set<Character> inorderSet = Set.copyOf(inorderInput);

        if (!preorderSet.equals(inorderSet)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }

        if (preorderSet.size() != sizePreorder || inorderSet.size() != sizeInorder) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }

        return new Tree(nodeFromTransversals(preorderInput, inorderInput));
    }
}
