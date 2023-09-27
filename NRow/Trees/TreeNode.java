package NRow.Trees;

import java.util.ArrayList;
import java.util.List;
import NRow.Board;

public class TreeNode {
    private float eval;
    private Board board;
    private List<TreeNode> children;

    public TreeNode(Board board) {
        this.board = board;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public float getEval() {
        return eval;
    }
    
    public Board getBoard(){
      return board;
    }

    public void setEval(float evaluation) {
      this.eval = evaluation;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}