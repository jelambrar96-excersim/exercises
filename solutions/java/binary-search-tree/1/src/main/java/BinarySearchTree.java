import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root = null;

    void insert(T value) {
        Node<T> newNode = new Node<>();
        newNode.data = value;
        if (this.root == null) {
            this.root = newNode;
            return;
        }
        Node<T> currentNode = this.root;
        Node<T> prevNode = null; 
        int comparition = 0;
        while (currentNode != null) {
            prevNode = currentNode;
            comparition = prevNode.getData().compareTo(value);
            currentNode = comparition >= 0 ? currentNode.getLeft() : currentNode.getRight();
            System.out.println(value);
            System.out.println(comparition);
        }
        if (comparition >= 0) {
            prevNode.left = newNode;
        }
        else {
            prevNode.right = newNode;
        }
    }

    private List<T> getAsSortedList(Node<T> node) {
        List<T> tList = new LinkedList<>();
        if (node.left != null) {
            tList.addAll(getAsSortedList(node.left));
        }
        tList.add(node.data);
        if (node.right != null) {
            tList.addAll(getAsSortedList(node.right));
        }
        return tList;
    }

    List<T> getAsSortedList() {
        return getAsSortedList(this.root);
    }

    List<T> getAsLevelOrderList() {
        List<T> values = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            values.add(node.getData());
            if (node.getLeft() != null) queue.add(node.getLeft());
            if (node.getRight() != null) queue.add(node.getRight());
        }
        return values;
    }

    Node<T> getRoot() {
        return this.root;
    }

    static class Node<T> {

        T data;
        Node<T> left = null;
        Node<T> right = null;

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

    }

    public static void main(String [] args) {
        BinarySearchTree<Character> binarySearchTree = new BinarySearchTree<>();

        char expectedRoot = '4';
        char expectedLeft = '2';

        binarySearchTree.insert(expectedRoot);
        binarySearchTree.insert(expectedLeft);
    }
}
