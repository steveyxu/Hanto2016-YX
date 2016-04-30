/**
 * 
 */
package hanto.studentyxu4.delta;

import static hanto.common.HantoPieceType.*;
import static hanto.studentyxu4.common.HantoMoveType.*;

import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.AbsHantoGame;
import hanto.studentyxu4.common.HantoBoard;
/**
 * 
 * @author steve
 *
 */
public class DeltaHantoGame extends AbsHantoGame {
	/**
	 * Constructor for DeltaHanto game.
	 * @param movesFirst is the color that moves first
	 */
	public DeltaHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard(movesFirst, true);
	
		board.addRule(BUTTERFLY, WALK, 1, 1);
		board.addRule(SPARROW, FLY, 4, Integer.MAX_VALUE);
		board.addRule(CRAB, WALK, 4, 3);
	}
}
