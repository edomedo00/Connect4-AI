package NRow.Players;

import NRow.Game;

import NRow.Board;
import NRow.Heuristics.Heuristic;
import NRow.Trees.Tree;
import NRow.Trees.TreeNode;
import NRow.Game;

public class MinMaxPlayer extends PlayerController {
    private int depth;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    /**
    * Implement this method yourself!
    * @param board the current board
    * @return column integer the player chose
    */
    @Override
    public int makeMove(Board board, Tree gameTree) {
      System.out.println("Players board");
      System.out.println(gameTree.getCurNode().getBoard());
      
      minimax(gameTree.getCurNode(), this.depth, 1);
      
      int bestIndex = 0;
      for(int i = 0; i<gameTree.getCurNode().getChildren().size();i++){
        if(gameTree.getCurNode().getChild(i).getEval(playerId) > gameTree.getCurNode().getChild(bestIndex).getEval(playerId)){
          bestIndex = i;
        }
      }
      return bestIndex;
    }

    public boolean boardIsFull(int[][] boardstate){
      for(int i = 0; i < boardstate.length; i++){
        for(int j = 0; j < boardstate[i].length; j++){
          if(boardstate[i][j] == 0){
            return false;
          }
        }
      }
      return true;
    }

    public int minimax(TreeNode curNode, int depth, int maximizingPlayer){
      // int eval = heuristic.evaluateBoard(playerId, curNode.getBoard());
      if(curNode.getBoard()==null){
        return Integer.MAX_VALUE;
      } // || boardIsFull(curNode.getBoard().getBoardState())
      if(depth == 0 ){
        int eval = heuristic.evaluateBoard(playerId, curNode.getBoard());
        return eval;
      }
      if(maximizingPlayer == 1){
        int maxEval = Integer.MIN_VALUE;
        for (TreeNode node : curNode.getChildren()) {
          if(node.getEval(playerId)!=Integer.MIN_VALUE){
            int eval = minimax(node, depth - 1, 2);
            maxEval = Integer.max(maxEval, eval);
          }
        }
        curNode.setEval(maxEval, playerId);
          
        return maxEval;
      } else{
        int minEval = Integer.MAX_VALUE; 
        for (TreeNode node : curNode.getChildren()) {
          if(node.getEval(playerId)!=Integer.MIN_VALUE){ 
            int eval = minimax(node, depth - 1, 1);
            minEval = Integer.min(minEval, eval);
          }
        }
        curNode.setEval(minEval, playerId);
        return minEval;
      }
    }

  @Override
  public int getDepth(){
    return this.depth;
  }
}

