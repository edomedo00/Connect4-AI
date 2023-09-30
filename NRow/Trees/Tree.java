package NRow.Trees;

import NRow.Board;

public class Tree {
    private TreeNode root;
    private Board board;
    private TreeNode curNode;

    public Tree(Board board) {
        this.board = board;
        this.root = new TreeNode(this.board);
        curNode = root;
    }

    public TreeNode getCurNode() {
        return curNode;
    }

    public void setCurNode(TreeNode newNode) {
        this.curNode = newNode;
    }
}