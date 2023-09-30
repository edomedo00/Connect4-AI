package NRow.Players;

import NRow.Board;
import NRow.Game;
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
      System.out.println(board);

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
        if (curNode.getChildren().isEmpty()){
          // u advance a lawyer and check if the children of the node of the lawyer exist 
          for (int n = 0; n < board.width ; n++){
            if(board.isValid(n)){
              Board newBoard = board.getNewBoard(n, playerId);
              TreeNode newChild = new TreeNode(newBoard);
              curNode.addChild(newChild);
            } else {
              TreeNode nullChild = new TreeNode();
              curNode.addChild(nullChild);            
            }    
          }
        }
      }

      
      

      // Move inside the tree
      // update the current node 
    
      int bestMove = getBestAction(curNode, board);
      this.gameTree.setCurNode(curNode.getChildren().get(bestMove));
        
      // gameTree.setCurNode(curNode.getChildren().get(bestMove));
      return bestMove;
    }

    public void treeGen (){}

    public int getBestAction(TreeNode curNode, Board board) {
      int[] moves = evalActions(curNode, board);
      int bestAction = 0;
      for (int i = 0; i < moves.length; i++) {
          bestAction = moves[i] > moves[bestAction] ? i : bestAction;
      }
      return bestAction;
    }

    public int[] evalActions(TreeNode curNode, Board board){
      //TreeNode curNodeCopy = curNode;
      minimax(curNode, depth, playerId);
      // this.gameTree.setCurNode(curNode); //
      int[] moves = new int[board.width];
      //System.out.println(moves.length);
      //System.out.println(board.width);
      for (int i = 0; i < board.width; i++) {
          moves[i] = curNode.getChildren().get(i).getEval();
          System.out.println(moves[i]);
      }
      return moves;
    }

      public int minimax(TreeNode curNode, int depth, int maximizingPlayer){
        int maxEval;
        int minEval;

        if(depth == 0){
          int eval = heuristic.evaluateBoard(playerId, curNode.getBoard());
          curNode.setEval(eval);
          System.out.println(eval);
          return eval; 
        }
        if(maximizingPlayer == 1){
          maxEval = Integer.MIN_VALUE;
          for (TreeNode node : curNode.getChildren()) {
            if(node.getEval()!=Integer.MIN_VALUE){
              int eval = minimax(node, depth - 1, 2);
              maxEval = Integer.max(maxEval, eval);
              //System.out.println(eval + " " + maxEval);
              curNode.setEval(maxEval);
            }
          }
            // this.gameTree.setCurNode(curNode);
            return maxEval;
            // return heuristic.evaluateBoard(playerId, curNode.getBoard()); 
          } else {
            minEval = Integer.MAX_VALUE;
            for (TreeNode node : curNode.getChildren()) {
              int eval = minimax(node, depth - 1, 1);
              minEval = Integer.max(minEval, eval);
              //System.out.println(eval + " " + minEval);
              curNode.setEval(minEval);
            }
            // this.gameTree.setCurNode(curNode);
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