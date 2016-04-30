/**
 * This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.state;

import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

/**
 * The states of a Hanto game
 * @author Yang Xu 
 */
public interface BoardState {
	/**
	 * Change the state of the game to next movement.
	 * @param moveResult the move result after a valid move 
	 * @param maxMove the largest allowed moves for a game
	 * @return the state of next movement  
	 * @throws HantoException 
	 */
	 BoardState nextMove(MoveResult moveResult, int maxMove) throws HantoException;
	
	/**
	 * Return the current player's color
	 * @return current player's color */
	 HantoPlayerColor currentColor();
	 
	/**
	 * Indicates weather the game is over.
	 * @return true if the game is over, false if the game is still in process
	 */
	 boolean isGameOver();
}
