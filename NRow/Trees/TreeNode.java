package NRow.Trees;

import java.util.ArrayList;
import java.util.List;
import NRow.Board;

public class TreeNode {
    private int[] eval;
    private Board board;
    private List<TreeNode> children;

    public TreeNode(Board board) {
        this.board = board;
        this.eval = new int[2];
        this.eval[0] = 0;
        this.eval[1] = 0;
        this.children = new ArrayList<>();
    }

    public TreeNode() {
        this.board = null;
        this.eval = new int[2];
        this.eval[0] = Integer.MIN_VALUE;
        this.eval[1] = Integer.MIN_VALUE;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public int getEval(int playerId) {
        return eval[playerId - 1];
    }

    public void setEval(int newEval, int playerId) {
        this.eval[playerId - 1] = newEval;
    }
    
    public Board getBoard(){
        return this.board;
    }

    public List<TreeNode> getChildren() {
        return this.children;
    }

    public TreeNode getChild(int index) {
        return this.children.get(index);
    }

    public Boolean noValidChildren(){
        for(TreeNode node : this.getChildren()){
            if(node.getBoard() != null){
                return false;
            }
        }
        return true;
    }
}