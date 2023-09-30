package NRow.Trees;

import java.util.ArrayList;
import java.util.List;
import NRow.Board;

public class TreeNode {
    private int eval;
    private Board board;
    private List<TreeNode> children;

    public TreeNode(Board board) {
        this.board = board;
        this.eval = 0;
        this.children = new ArrayList<>();
    }

    public TreeNode() {
        this.eval = Integer.MIN_VALUE;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public int getEval() {
        return eval;
    }
    
    public Board getBoard(){
      return board;
    }

    public void setEval(int evaluation) {
      this.eval = evaluation;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}