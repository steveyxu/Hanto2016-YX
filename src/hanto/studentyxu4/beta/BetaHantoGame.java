/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package hanto.studentyxu4.beta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.studentyxu4.common.HantoMoveType.STILL;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentyxu4.common.HantoBoard;
import hanto.studentyxu4.common.HantoCoordinateImpl;


/**
 * Beta Hanto Game 
 * @author Yang Xu yxu4
 * @version Apr 25, 2016
 */
public class BetaHantoGame implements HantoGame
{
	private final int maxTurn = 12;
	HantoBoard board;
	
	/**
	 * Constructor for BetaHanto if the starting color is specified
	 * @param movesFirst the color that moves first
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		board = new HantoBoard(movesFirst, false, maxTurn);
		
		board.addRule(BUTTERFLY, STILL, 1, 0);
		board.addRule(SPARROW, STILL, 5, 0);
		board.enableDiffColorPlacement();
	}

	/**
	 * The main function makes Hanto piece to move or place on the borad
	 * @param pieceType the type of the piece being operated
	 * @param from the original location of the piece, not used in betaHanto
	 * @param to the target location of the piece
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, 
	 * hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException
	{
		final HantoCoordinateImpl source = (from == null) ? null : new HantoCoordinateImpl(from);
		final HantoCoordinateImpl destination = (to == null) ? null : new HantoCoordinateImpl(to);
		
		board.makeMove(pieceType, source, destination);
		
		final MoveResult moveResult = board.getMoveResult();
		
		return moveResult;
	}



	/**
	 * @param where the coordinate of the hex that we want to get its item type
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
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
		System.out.println("dummy output");
		return null;
	}

}
