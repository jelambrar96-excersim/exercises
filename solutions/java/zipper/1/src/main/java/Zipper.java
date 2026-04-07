import java.util.Objects;

class Zipper {
    Zipper up;
    Zipper left;
    Zipper right;

    private int value;

    public Zipper(int val) {
        this.value = val;
        this.up = null;
        this.left = null;
        this.right = null;
    }

    public BinaryTree toTree() {
        return new BinaryTree(this);
    }

    public int getValue() {
        return value;
    }

    public Zipper setLeft(Zipper leftChild) {
        this.left = leftChild;
        if (this.left == null) { return this; }
        this.left.up = this;
        return this;
    }

    public Zipper setRight(Zipper rightChild) {
        this.right = rightChild;
        if (this.right == null) { return this; }
        this.right.up = this;
        return this;
    }

    public void setValue(int val) {
        this.value = val;
    }

    @Override
    public String toString() {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("value: ");
        sbuilder.append(Integer.toString(this.getValue()));
        sbuilder.append(", left: ");
        if (left == null) { sbuilder.append("null"); }
        else { sbuilder.append("{ " + this.left.toString() + " }"); }
        sbuilder.append(", right: ");
        if (right == null) { sbuilder.append("null"); }
        else { sbuilder.append("{ " + this.right.toString() + " }");}
        return sbuilder.toString();
    }

}

class BinaryTree {

    private Zipper root;    

    public BinaryTree(int value) {
        this.root = new Zipper(value);
    }

    public BinaryTree(Zipper root) {
        this.root = root;
        while (this.root.up != null) {
            this.root = this.root.up;
        }
    }

    public Zipper getRoot() {
        return this.root;
    }

    public String printTree() {
        return this.root.toString();        
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        BinaryTree bn = (BinaryTree)o;
        return Objects.equals(this.root, bn.getRoot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

}
