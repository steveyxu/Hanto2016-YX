package hanto.studentyxu4.epsilon;

import static hanto.common.HantoPieceType.*;
import static hanto.studentyxu4.common.HantoMoveType.*;

import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.AbsHantoGame;
import hanto.studentyxu4.common.HantoBoard;

public class EpsilonHantoGame extends AbsHantoGame {
	/**
	 * Default constructor for Epsilon Hanto
	 * @param movesFirst
	 */
	public EpsilonHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard(movesFirst, true);
		
		board.addRule(BUTTERFLY, WALK, 1, 1);
		board.addRule(SPARROW, FLY, 2, 4);
		board.addRule(CRAB, WALK, 6, 1);
		board.addRule(HORSE, JUMP, 4, 3);
	}

}
