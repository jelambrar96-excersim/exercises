import java.util.ArrayList;

class TreeNode implements Comparable<TreeNode> {
    private int nodeId;
    private ArrayList<TreeNode> children;

    TreeNode(int nodeId) {
        this.nodeId = nodeId;
        this.children = new ArrayList<>();
    }

    int getNodeId() {
        return nodeId;
    }

    void addChildrenNode(TreeNode node) {
        this.children.add(node); 
        this.children.sort(TreeNode::compareTo);
    }

    ArrayList<TreeNode> getChildren() {
        return children;
    }

    @Override
    public int compareTo(TreeNode arg0) {
        return this.nodeId - arg0.getNodeId();
    }
    
}
