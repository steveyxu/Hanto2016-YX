package hanto.studentyxu4.gamma;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static hanto.common.MoveResult.*;
import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.MoveResult;
import hanto.studentyxu4.HantoGameFactory;


public class GammaHantoMasterTest {
	/**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		public TestHantoCoordinate(int x, int y)
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
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
	private static HantoGameFactory factory;
	private HantoGame game;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
	}
	
	@Test	// 1 - 1
	public void bluePlacesInitialButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test	// 1 - 2
	public void redPlacesInitialButterflyAtOrigin() throws HantoException
	{
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO, RED);// Red first
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test	// 2 - 1 
	public void bluePlacesInitialSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test	// 2- 2 
	public void redPlacesInitialSparrowAtOrigin() throws HantoException
	{
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO, RED);	// RedFirst
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test (expected = HantoException.class)// 3 
	public void invalidSecondMove() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 1));
	}
	
	@Test (expected = HantoException.class) //4
	public void invalidThirdPiecePlacement() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));//b
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));//r
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		assertNull(game.getPieceAt(makeCoordinate(1, -1)));
	}
	
	@Test // 5
	public void validThirdPiecePlacement() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));//b
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));//r
		final MoveResult mResult = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		assertEquals(OK, mResult);
		final HantoPiece p = game.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected = HantoException.class)	// 6
	public void firstMoveIsNotAtOrigin() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
	}
	
	@Test(expected = HantoException.class)	// 7 - 1 
	public void blueAttemptsToPlaceTwoButterflies() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
	}
	
	@Test(expected = HantoException.class)	//  7 - 2 
	public void redAttemptsToPlaceTwoButterflies() throws HantoException
	{
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO, RED);
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
	}
	
	@Test(expected = HantoException.class)	// 8 - 1 
	public void blueTriesToPlacePieceOnOccupiedHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test(expected = HantoException.class)	// 8  - 2 
	public void redTriesToPlacePieceOnOccupiedHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, 	null, makeCoordinate(0, -1));
	}
	
	@Test(expected = HantoException.class)	// 9 - 1 
	public void blueDoesNotPlaceButterflyByFourthMove() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));	// Move 1
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));	// Move 2
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));	// Move 3
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));	// Move 4
	}
	
	@Test(expected = HantoException.class)	// 9 - 2
	public void redDoesNotPlaceButterflyByFourthTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));	// Move 1
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));	// Move 2
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));	// Move 3
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));	// Move 4
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
	}
	
	@Test (expected = HantoException.class)//10 
	public void placeMoreThenLimitedSparrows() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));//b2
		game.makeMove(SPARROW, 	null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));//b3
		game.makeMove(SPARROW, 	null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-4, 0));//b4
		game.makeMove(SPARROW, 	null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-5, 0));//b5
		game.makeMove(SPARROW, 	null, makeCoordinate(6, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-6, 0));//b6
	}
	
	@Test (expected = HantoException.class)//10 -2  
	public void placeMoreThenLimitedSparrows2() throws HantoException
	{
		game =  factory.makeHantoGame(HantoGameID.GAMMA_HANTO, RED);
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));//b2
		game.makeMove(SPARROW, 	null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));//b3
		game.makeMove(SPARROW, 	null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-4, 0));//b4
		game.makeMove(SPARROW, 	null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-5, 0));//b5
		game.makeMove(SPARROW, 	null, makeCoordinate(6, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-6, 0));//b6
	}
	
	@Test (expected = HantoException.class) //11
	public void moveNotExistedPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
	}
	
	@Test (expected = HantoException.class) //12 - 1
	public void moveInvalidPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW,  makeCoordinate(2, 0), makeCoordinate(1, 1));
	}
	
	@Test (expected = HantoException.class) //12 - 2 
	public void moveInvalidPieceType() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(BUTTERFLY,  makeCoordinate(-1, 0), makeCoordinate(-1, 1));
	}
	
	
	@Test (expected = HantoException.class) //13
	public void breakSelfConnection() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW,  makeCoordinate(-1, 0), makeCoordinate(-2, 0));
	}
	
	@Test (expected = HantoException.class) //14 - 1
	public void breakConnection() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));//b2
		game.makeMove(SPARROW, 	null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));//b3
		game.makeMove(SPARROW, 	null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-4, 0));//b4
		game.makeMove(SPARROW, 	null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-5, 0));//b5
		game.makeMove(SPARROW, 	null, makeCoordinate(6, 0));
		game.makeMove(BUTTERFLY,  makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test //14 - 2
	public void validMove() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));//b2
		game.makeMove(SPARROW, 	null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));//b3
		game.makeMove(SPARROW, 	null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-4, 0));//b4
		game.makeMove(SPARROW, 	null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-5, 0));//b5
		game.makeMove(SPARROW, 	null, makeCoordinate(6, 0));
		game.makeMove(SPARROW,  makeCoordinate(-5, 0), makeCoordinate(-5, 1));
	}
	
	@Test (expected = HantoException.class) //15
	public void moveOutOfRange() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW,  makeCoordinate(-1, 0), makeCoordinate(-1, 2));
	}
	
	@Test (expected = HantoException.class)//16
	public void moveOutThroughNarrowSpace() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));//b2
		game.makeMove(SPARROW, 	null, makeCoordinate(2, -2));
		game.makeMove(BUTTERFLY,  makeCoordinate(0, 0), makeCoordinate(1, -1));
	}
	
	@Test	(expected = HantoException.class)// 17
	public void drawnGame() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));//b1
		game.makeMove(SPARROW, 	null, makeCoordinate(2, 0));
		game.makeMove(SPARROW,  makeCoordinate(-1, 0), null);
	}
	
	@Test	// 17 - 2
	public void drawnGame2() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));	// Move 1
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));	// Move 2
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));	// Move 3
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));		// Move 4
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 1));		// Move 5
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, makeCoordinate(-2, 1),makeCoordinate(-1, 1));
		assertEquals(DRAW, game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0)));
	}
	
	@Test	(expected = HantoException.class)// 18
	public void moveAfterGameOver() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));	// Move 1
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));	// Move 2
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));	// Move 3
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));		// Move 4
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 1));		// Move 5
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, makeCoordinate(-2, 1),makeCoordinate(-1, 1));
		assertEquals(DRAW, game.makeMove(SPARROW, makeCoordinate(2, 0), makeCoordinate(1, 0)));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 1));
	}
	
	@Test
	public void printNotnull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));	// Move 1
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertNotNull(game.getPrintableBoard());
	}
	
	@Test (expected = HantoException.class)
	public void otherTypePiece() throws HantoException{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
	}


}
