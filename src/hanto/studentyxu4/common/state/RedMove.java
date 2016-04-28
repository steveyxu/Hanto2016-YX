/**
 * This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.state;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

/**
 * The state of blue is currently playing
 * @author Yang Xu
 */

public class RedMove implements BoardState {
    private final int moveCounter;
    
    /**
     * The default constructor for RedMove.
     */
    public RedMove(){
    	moveCounter = 0;
    }
    
    /**
     * The constructor with move counter initiated.   
	 * @param moveCounter the current move number in the game 
     */
	public RedMove(int moveCounter) {
		this.moveCounter = moveCounter;
	}
	
	@Override
	public BoardState nextMove(MoveResult moveResult, int maxMove) {
		return (moveCounter < maxMove - 1) ? new BlueMove(moveCounter + 1)
										   : new GameOver();
	}

	@Override
	public HantoPlayerColor currentColor() {
		return HantoPlayerColor.RED;
	}

	@Override
	public boolean isGameOver() {
		return false;
	}
	
}
