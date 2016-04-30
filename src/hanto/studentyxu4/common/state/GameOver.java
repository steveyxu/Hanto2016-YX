/**
 * This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.state;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
/**
 * The state of the game is over. 
 * @author Yang Xu
 */
public class GameOver implements BoardState {

		
	@Override
	public BoardState nextMove(MoveResult moveResult, int maxMove) {
		return null;
	}

	@Override
	public HantoPlayerColor currentColor() {
		return null;
	}

	@Override
	public boolean isGameOver() {
		return true;
	}
	

}
