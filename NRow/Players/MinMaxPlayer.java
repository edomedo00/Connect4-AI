package NRow.Players;

import NRow.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
      // System.out.println("Players board");
      // System.out.println(gameTree.getCurNode().getBoard());
      
      minimax(gameTree.getCurNode(), this.depth, 1);
      
      int bestIndex = 0;
      for(int i = 0; i<gameTree.getCurNode().getChildren().size();i++){
        System.out.println(gameTree.getCurNode().getChild(i).getEval(playerId));
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
        if(maximizingPlayer == 1){
          return Integer.MAX_VALUE;
        } else {
          return Integer.MIN_VALUE;
        }
      }
      if(depth == 1 || curNode.noValidChildren()){
        int eval = heuristic.evaluateBoard(playerId, curNode.getBoard());
        //System.out.println(eval);
        return eval;
      }
      if(maximizingPlayer == 1){
        int maxEval = Integer.MIN_VALUE;
        for (TreeNode node : curNode.getChildren()) {
          int eval = node.getEval(playerId);
          // if(eval!=Integer.MIN_VALUE){
            eval = minimax(node, depth - 1, 2);
          // }
          maxEval = Integer.max(maxEval, eval);
        }
        curNode.setEval(maxEval, playerId);
        return maxEval;
      } else{
        int minEval = Integer.MAX_VALUE; 
        for (TreeNode node : curNode.getChildren()) {
          int eval = node.getEval(playerId);

          // if(eval!=Integer.MIN_VALUE){ 
            eval = minimax(node, depth - 1, 1);
          // }
          minEval = Integer.min(minEval, eval);

        }
        curNode.setEval(minEval, playerId);
        return minEval;
      }
    }

    // public int minimax(TreeNode curNode, int playerCur){
    //     int winner = Game.winning(curNode.getBoard().getBoardState(), gameN);
    //     if (winner > 0){ //check if there is a winning board and stop the recursive call
    //         return winner == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    //     }
    //     else if (curNode.getChildren().size() == 0){
    //         return heuristic.evaluateBoard(playerCur, curNode.getBoard());
    //     } else {
    //         List<Integer> scoreList = new ArrayList<Integer>();
    //         curNode.getChildren().forEach(child -> scoreList.add(minimax(child, playerCur == 1 ? 2 : 1))); //calculates the score of each child
    //         int bestScore;
    //         if (playerCur == 1){
    //             bestScore = Collections.max(scoreList);
    //         }
    //         else {
    //             bestScore = Collections.min(scoreList);
    //         }
    //         return bestScore; //returns the best move
    //     }
    // }

  @Override
  public int getDepth(){
    return this.depth;
  }
}

