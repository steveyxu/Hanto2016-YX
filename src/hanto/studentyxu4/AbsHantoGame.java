package hanto.studentyxu4;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentyxu4.common.HantoBoard;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.tournament.HantoMoveRecord;

public abstract class AbsHantoGame implements HantoGame {
	protected HantoBoard board;
	
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

	/**
	 * Print the status of the current game
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 * @return a string indicates the current status
	 */
	@Override
	public String getPrintableBoard()
	{
		return ("dummy output");
	}
	
	/**
	 * Make a valid move for the tournament player
	 * @return
	 */
	public HantoMoveRecord generateMove(){
		return null;
		
	}

}
