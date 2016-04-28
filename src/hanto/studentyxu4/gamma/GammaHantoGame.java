/**
 * Gamma Hanto By Yang Xu 
 * yxu4@wpi.edu
 */
package hanto.studentyxu4.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentyxu4.common.HantoBoard;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import static hanto.common.HantoPieceType.*;
import static hanto.studentyxu4.common.HantoMoveType.*;
/**
 * The Main class for Gamma Hanto
 * @author Yang Xu
 */
public class GammaHantoGame implements HantoGame {
	private final int maxTurn = 40;
	HantoBoard board;
	
	/**
	 * Constructor for GammaHanto game.
	 * @param movesFirst is the color that moves first
	 */
	public GammaHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard(movesFirst, false, maxTurn);
	
		board.addRule(BUTTERFLY, WALK, 1, 1);
		board.addRule(SPARROW, WALK, 5, 1);
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		
		final HantoCoordinateImpl source = (from == null) ? null : new HantoCoordinateImpl(from);
		final HantoCoordinateImpl destination = (to == null) ? null : new HantoCoordinateImpl(to);
		
		board.makeMove(pieceType, source, destination);
		
		final MoveResult moveResult = board.getMoveResult();
		
		return moveResult;
	} 
	
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		final HantoCoordinateImpl coordinate = new HantoCoordinateImpl(where);
		HantoPiece result = null;
		result = board.getPieceAt(coordinate);
		return result;
	}
	
	@Override
	public String getPrintableBoard() {
		return "dummy output";
	}
}
