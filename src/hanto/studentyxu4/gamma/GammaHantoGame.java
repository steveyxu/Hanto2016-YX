/**
 * Gamma Hanto By Yang Xu 
 * yxu4@wpi.edu
 */
package hanto.studentyxu4.gamma;

import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.AbsHantoGame;
import hanto.studentyxu4.common.HantoBoard;
import static hanto.common.HantoPieceType.*;
import static hanto.studentyxu4.common.HantoMoveType.*;
/**
 * The Main class for Gamma Hanto
 * @author Yang Xu
 */
public class GammaHantoGame extends AbsHantoGame {
	private final int maxTurn = 40;
	
	/**
	 * Constructor for GammaHanto game.
	 * @param movesFirst is the color that moves first
	 */
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard(movesFirst, false, maxTurn);
	
		board.addRule(BUTTERFLY, WALK, 1, 1);
		board.addRule(SPARROW, WALK, 5, 1);
	}
}
