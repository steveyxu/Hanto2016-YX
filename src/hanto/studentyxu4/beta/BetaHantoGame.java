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

import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.AbsHantoGame;
import hanto.studentyxu4.common.HantoBoard;


/**
 * Beta Hanto Game 
 * @author Yang Xu yxu4
 * @version Apr 25, 2016
 */
public class BetaHantoGame extends AbsHantoGame
{
	private final int maxTurn = 12;
	
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
}
