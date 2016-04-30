/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentyxu4.tournament;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.AbsHantoGame;
import hanto.studentyxu4.HantoGameFactory;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * The AI Hanto player class
 * @version Oct 13, 2014
 */
public class HantoPlayer implements HantoGamePlayer
{
	private HantoPlayerColor myColor;
	private HantoGame game;
	
	/*
	 * @see hanto.tournament.HantoGamePlayer#startGame(hanto.common.HantoGameID, hanto.common.HantoPlayerColor, boolean)
	 */
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst)
	{
		this.myColor = myColor;
		HantoPlayerColor movesFirst;
		if (doIMoveFirst) {
			game = HantoGameFactory.getInstance().makeHantoGame(version, myColor);
		}
		else {
			movesFirst = myColor == BLUE ? RED : BLUE;
			game = HantoGameFactory.getInstance().makeHantoGame(version, movesFirst);
		}
	}

	/*
	 * @see hanto.tournament.HantoGamePlayer#makeMove(hanto.tournament.HantoMoveRecord)
	 */
	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove)
	{
		if (opponentsMove == null) {
			try {
				game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
			}
			catch (HantoException e){
				e.getMessage();
			}
			return new HantoMoveRecord(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		}
		final HantoPieceType oppPieceType = opponentsMove.getPiece();
		final HantoCoordinate oppFrom = opponentsMove.getFrom();
		final HantoCoordinate oppTo = opponentsMove.getTo();
		try {
			game.makeMove(oppPieceType, oppFrom, oppTo);
		}
		catch (HantoException e) {
			System.out.println(e.getMessage());
		}
		
		return ((AbsHantoGame) game).generateMove(myColor);
	}

}
