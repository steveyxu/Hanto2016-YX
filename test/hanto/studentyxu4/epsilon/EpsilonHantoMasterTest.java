package hanto.studentyxu4.epsilon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;

import static hanto.common.HantoGameID.*;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static hanto.common.MoveResult.*;

import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentyxu4.HantoGameFactory;
import hanto.studentyxu4.epsilon.EpsilonHantoGame;


public class EpsilonHantoMasterTest {
	class MoveData {
		final HantoPieceType type;
		final HantoCoordinate from, to;
		
		private MoveData(HantoPieceType type, HantoCoordinate from, HantoCoordinate to) 
		{
			this.type = type;
			this.from = from;
			this.to = to;
		}
	}

	/**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	class TestHantoCoordinate implements HantoCoordinate {
		private final int x, y;
	
		/**
		 * TestHantoCoordinate Constructor
		 * @param x coordinate
		 * @param y coordinate
		 */
		TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

	}

	private static HantoGameFactory factory = HantoGameFactory.getInstance();
	private HantoGame game;

	
	private MoveData md(HantoPieceType type, int toX, int toY) 
	{
		return new MoveData(type, null, makeCoordinate(toX, toY));
	}

	private MoveData md(HantoPieceType type, int fromX, int fromY, int toX, int toY)
	{
		return new MoveData(type, makeCoordinate(fromX, fromY), makeCoordinate(toX, toY));
	}
	
	/**
	 * Make the moves specified. If there is no exception, return the move result of
	 * the last move.
	 * @param moves
	 * @return the last move result
	 * @throws HantoException
	 */
	private MoveResult makeMoves(MoveData... moves) throws HantoException
	{
		MoveResult mr = null;
		for (MoveData md : moves) {
			mr = game.makeMove(md.type, md.from, md.to);
		}
		return mr;
	}
	/**
	 * helper method
	 * @param x
	 * @param y
	 * @return HantoCoordinate created
	 */
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	/**
	 *  initialize the factory 
	 */
	@BeforeClass
	public static void initializeClass() {
		factory = HantoGameFactory.getInstance();
	}

	/**
	 * setup the beta hanto game
	 */
	@Before
	public void setup() {
		// By default, blue moves first.
		game = factory.makeHantoGame(EPSILON_HANTO);
	}
	
	@Test
	public void getEpsilonHantoGameFromTheFactory()
	{
		assertTrue(game instanceof EpsilonHantoGame);
	}
	
	@Test
	public void validJump() throws HantoException{
		final MoveResult mr = makeMoves(md(BUTTERFLY, 0, 0), md(BUTTERFLY, 0, 1),
										md(HORSE, -1, 0),md(HORSE, 0, 2),
										md(HORSE, -1, 0,1,0),md(HORSE, 0, 2,0,-1));
		final HantoPiece piece = game.getPieceAt(makeCoordinate(0, -1));
		assertEquals(RED, piece.getColor());
		assertEquals(HORSE, piece.getType());
		assertEquals(OK, mr);
	}
	
	@Test (expected = HantoException.class)
	public void inValidJump() throws HantoException{
		makeMoves(md(BUTTERFLY, 0, 0), md(BUTTERFLY, 0, 1),
				  md(HORSE, -1, 0),md(HORSE, 0, 2),
				  md(SPARROW, -1, -1),md(HORSE, 0, 2,0,-2));
	}
	
	
}
