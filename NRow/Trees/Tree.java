package NRow.Trees;

import java.util.ArrayList;
import java.util.List;
import NRow.Board;
import NRow.Trees.TreeNode;

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
}