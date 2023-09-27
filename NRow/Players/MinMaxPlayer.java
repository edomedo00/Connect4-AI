package NRow.Players;

import NRow.Board;
import NRow.Heuristics.Heuristic;
import NRow.Trees.Tree;
import NRow.Trees.TreeNode;

public class MinMaxPlayer extends PlayerController {
    private int depth;
    private Tree gameTree;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic, Board board) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        gameTree = new Tree(board);

        //You can add functionality which runs when the player is first created (before the game starts)
    }

    /**
   * Implement this method yourself!
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {
        // TODO: implement minmax player!
        // HINT: use the functions on the 'board' object to produce a new board given a specific move
        // HINT: use the functions on the 'heuristic' object to produce evaluations for the different board states!

      //   function minimax(position, depth, maximizingPlayer){
	    //   if( depth == 0 ) // or game over in position
		  //       return static_evaluation_of_position;
 
      //   if (maximizingPlayer){
      //     maxEval = -infinity;
      //     foreach (child in position){
      //       eval = minimax(child, depth - 1, false);
      //       maxEval = max(maxEval, eval);
      //     }
      //     return maxEval;
      //   }
      // }
        
        TreeNode curNode = gameTree.getCurNode();
        for (int d = 0; d < depth; d++){
          for (int n = 0; n < gameN ; n++){
            if(board.isValid(n)){
              Board newBoard = board.getNewBoard(n, playerId);
              int eval = heuristic.evaluateBoard(playerId, newBoard); //evaluate that new board to get a heuristic value from it  
              TreeNode newChild = new TreeNode(newBoard);
              newChild.setEval(eval);     
              curNode.addChild(new TreeNode(newBoard));
            }       
          }
        }

        int bestMove = minimax(curNode, depth, playerId);
        
        return bestMove;
    }

        public int minimax(TreeNode curNode, int depth, int maximizingPlayer){
          int maxEval;
          int minEval;
          if(depth == 0)
            return heuristic.evaluateBoard(playerId, curNode.getBoard()); 

          if(maximizingPlayer == 1){
            maxEval = Integer.MIN_VALUE;
            for (TreeNode node : curNode.getChildren()) {
              int eval = minimax(node, depth - 1, 2);
              maxEval = Integer.max(maxEval, eval);
            }
            return maxEval;
          } else {
            minEval = Integer.MAX_VALUE;
            for (TreeNode node : curNode.getChildren()) {
              int eval = minimax(node, depth - 1, 1);
              minEval = Integer.max(minEval, eval);
            }
            return minEval;
          }
        }
        
        // Example: 
    //     int maxValue = Integer.MIN_VALUE;
    //     int maxMove = 0;
    //     for(int i = 0; i < board.width; i++) { //for each of the possible moves
    //         if(board.isValid(i)) { //if the move is valid
    //             Board newBoard = board.getNewBoard(i, playerId); // Get a new board resulting from that move
    //             int value = heuristic.evaluateBoard(playerId, newBoard); //evaluate that new board to get a heuristic value from it
    //             if(value > maxValue) {
    //                 maxMove = i;
    //             }
    //         }
    //     }
    //     // This returns the same as:
    //     heuristic.getBestAction(playerId, board); // Very useful helper function!
        

    //     /*
    //     This is obviously not enough (this is depth 1)
    //     Your assignment is to create a data structure (tree) to store the gameboards such that you can evaluate a higher depths.
    //     Then, use the minmax algorithm to search through this tree to find the best move/action to take!
    //     */
        
        
    // }
  }