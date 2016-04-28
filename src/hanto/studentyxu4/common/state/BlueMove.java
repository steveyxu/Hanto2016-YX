/**
 * This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.state;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
/**
 * The state of blue is currently playing
 * @author Yang Xu 
 * @version Apr 19, 2016
 */
public class BlueMove implements BoardState {
	private final int moveCounter;
	
	/**
	 * The default constructor for BlueMove
	 */
	public BlueMove () {
		moveCounter = 0;
	}
	/**
	 * The constructor with move counter initiated.   
	 * @param moveCounter the current move number in the game 
	 */
	public BlueMove(int moveCounter) {
		this.moveCounter = moveCounter;
	}
	
	@Override
	public BoardState nextMove(MoveResult moveResult, int maxMove) {
		return (moveCounter < maxMove - 1) ? new RedMove(moveCounter + 1) 
										   : new GameOver();
	}

	@Override
	public HantoPlayerColor currentColor() {
		return HantoPlayerColor.BLUE;
	}

	@Override
	public boolean isGameOver() {
		return false;
	}

}
