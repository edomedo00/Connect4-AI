package NRow.Trees;

import java.util.Arrays;

import NRow.Board;

public class Tree {
    private TreeNode root;
    private TreeNode curNode;

    public Tree(Board board) {
        this.root = new TreeNode(board);
        this.curNode = root;
    }
    
    public void treeGen (TreeNode curNode, Board board, int depth, int curPlayer){
      if(depth == 0){
        return;
      }
      if(!emptyTreeNode(curNode)){ // if it is not empty
        for(int n = 0; n < board.width; n++){
          if (curNode.getChild(n).getEval(curPlayer) != Integer.MIN_VALUE) //If not a null child
              treeGen(curNode.getChild(n), curNode.getChild(n).getBoard(), depth-1, changePlayer(curPlayer));
        }
      } else { // is empty
        for (int n = 0; n < board.width; n++){
          if(board.isValid(n)){
            Board newBoard = board.getNewBoard(n, curPlayer);
            TreeNode newChild = new TreeNode(newBoard);
            curNode.addChild(newChild);
          } else {
            TreeNode nullChild = new TreeNode();
            curNode.addChild(nullChild);
          }
        }
      treeGen(curNode, board, depth, curPlayer);
      }
    }

    public TreeNode findNewNode(Board newBoard){
        for(TreeNode node : this.curNode.getChildren()){
            if(newBoard.equals(node.getBoard())){
                return node;
            }
        }
        System.out.println("EMPTY TREENODE");
        return new TreeNode();
    }


    public void updateCurNode(int nextMove, Board newBoard){
        // this.curNode = findNewNode(newBoard);
        this.curNode = curNode.getChild(nextMove);
        // for(TreeNode node : this.curNode.getChildren()){
        //   if (node.getBoard() != null){
        //     System.out.println("Non null child teheee");
        //   }
        //   else{
        //     System.out.println("null child teheee");
        //   }
        // }
        // if(curNode.getBoard() == null){
        //   System.out.println("asdas");
        // }
        //System.out.println(newBoard.equalsss(curNode.getBoard()));
    }

    public TreeNode getCurNode() {
        return curNode;
    }

    public void setCurNode(TreeNode newNode) {
        this.curNode = newNode;
    }
    
    public int changePlayer(int curPlayer){
        return curPlayer == 1 ? 2 : 1;
    }

    public boolean emptyTreeNode(TreeNode curNode){
      return curNode.getChildren().isEmpty();
    }


}  
