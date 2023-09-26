import java.util.ArrayList;
import java.util.List;

class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public T getData() {
        return data;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }
}

public class MultiChildrenTree<T> {
    private TreeNode<T> root;

    public MultiChildrenTree(T rootData) {
        root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public static void main(String[] args) {
        // Create a multi-children tree
        MultiChildrenTree<String> tree = new MultiChildrenTree<>("Root");

        TreeNode<String> node1 = new TreeNode<>("Node 1");
        TreeNode<String> node2 = new TreeNode<>("Node 2");
        TreeNode<String> node3 = new TreeNode<>("Node 3");

        tree.getRoot().addChild(node1);
        tree.getRoot().addChild(node2);
        tree.getRoot().addChild(node3);

        TreeNode<String> node11 = new TreeNode<>("Node 1.1");
        TreeNode<String> node12 = new TreeNode<>("Node 1.2");

        node1.addChild(node11);
        node1.addChild(node12);

        TreeNode<String> node21 = new TreeNode<>("Node 2.1");

        node2.addChild(node21);

        // You can traverse the tree using various algorithms (e.g., depth-first or breadth-first)
        // For example, you can implement a recursive depth-first traversal method.
        System.out.println("Depth-First Traversal:");
        depthFirstTraversal(tree.getRoot());
    }

    public static void depthFirstTraversal(TreeNode<String> node) {
        if (node != null) {
            System.out.println(node.getData());
            for (TreeNode<String> child : node.getChildren()) {
                depthFirstTraversal(child);
            }
        }
    }
}