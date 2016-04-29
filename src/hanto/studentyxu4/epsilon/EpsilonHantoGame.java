package hanto.studentyxu4.epsilon;

import static hanto.common.HantoPieceType.*;
import static hanto.studentyxu4.common.HantoMoveType.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentyxu4.common.HantoBoard;
import hanto.studentyxu4.common.HantoCoordinateImpl;

public class EpsilonHantoGame implements HantoGame {
	private HantoBoard board;

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
		System.out.println("dummy output");
		return null;
	}

}
