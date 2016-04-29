/**
 * The Hanto Board
 * @author steve Yang Xu yxu4
 */
package hanto.studentyxu4.common;

import static hanto.common.MoveResult.BLUE_WINS;
import static hanto.common.MoveResult.DRAW;
import static hanto.common.MoveResult.OK;
import static hanto.common.MoveResult.RED_WINS;
import static hanto.studentyxu4.common.AdjacentPosition.DOWN;
import static hanto.studentyxu4.common.AdjacentPosition.DOWNLEFT;
import static hanto.studentyxu4.common.AdjacentPosition.DOWNRIGHT;
import static hanto.studentyxu4.common.AdjacentPosition.UP;
import static hanto.studentyxu4.common.AdjacentPosition.UPLEFT;
import static hanto.studentyxu4.common.AdjacentPosition.UPRIGHT;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentyxu4.common.state.BlueMove;
import hanto.studentyxu4.common.state.BoardState;
import hanto.studentyxu4.common.state.GameOver;
import hanto.studentyxu4.common.state.RedMove;
import hanto.studentyxu4.common.validator.ButterFlyValidator;
import hanto.studentyxu4.common.validator.ConnectionValidator;
import hanto.studentyxu4.common.validator.FlyValidator;
import hanto.studentyxu4.common.validator.JumpValidator;
import hanto.studentyxu4.common.validator.MoveValidatorStrategy;
import hanto.studentyxu4.common.validator.PlaceValidator;
import hanto.studentyxu4.common.validator.StillValidator;
import hanto.studentyxu4.common.validator.WalkValidator;
/**
 * 
 * @author steve
 *
 */
public class HantoBoard {
	
	private Map<HantoPieceType, MoveRule> ruleSet;
	private Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable;
	private BoardState boardState;
	private boolean allowResign = false;
	private int maxMove = 65536;
	private HantoCoordinateImpl blueButterflyHex = null, redButterflyHex = null;
	private MoveResult moveResult = null;
	private int moveCounter = 0;
	private int maxButterflyTurns = 3;
	boolean diffColorNearby = false;
	
	/**
	 * Constructor
	 * @param moveFirst
	 * @param allowResign
	 */
	public HantoBoard(HantoPlayerColor moveFirst, boolean allowResign){
		ruleSet = new HashMap<HantoPieceType, MoveRule>();
		coordinateTable = new Hashtable<HantoCoordinateImpl, HantoPieceImpl>();
		this.allowResign = allowResign;
		switch (moveFirst) {
		case RED:
			boardState = new RedMove();
			break;
		case BLUE:
			boardState = new BlueMove();
		}
	}
	
	/**
	 * Constructor
	 * @param moveFirst
	 * @param allowResign
	 * @param maxMove
	 */
	public HantoBoard(HantoPlayerColor moveFirst, boolean allowResign, int maxMove){
		ruleSet = new HashMap<HantoPieceType, MoveRule>();
		coordinateTable = new Hashtable<HantoCoordinateImpl, HantoPieceImpl>();
		this.allowResign = allowResign;
		this.maxMove = maxMove;
		switch (moveFirst) {
		case RED:
			boardState = new RedMove();
			break;
		case BLUE:
			boardState = new BlueMove();
		}
	}
	
	/**
	 * Add a new rule to the board
	 * @param pieceType a
	 * @param moveType a
	 * @param distance a
	 * @param maxNum a
	 */
	public void addRule(HantoPieceType pieceType, HantoMoveType moveType, int maxNum, int distance){
		MoveRule moveRule = new MoveRule(distance, maxNum, moveType);
		ruleSet.put(pieceType, moveRule);
	}
	/**
	 * 
	 * @param pieceType
	 * @param from
	 * @param to
	
	 * @throws HantoException */
	public void makeMove(HantoPieceType pieceType, HantoCoordinateImpl from, HantoCoordinateImpl to) throws HantoException{
		
		if (boardState.isGameOver()) {
			throw new HantoException("You cannot move after the game is finished");
		}
		
		if ((pieceType == null)&&(from == null) && (to == null)){
			resign();
			return;
		}
		else if (!ruleSet.containsKey(pieceType)) {
			throw new HantoException("This piece is not allowed for this version of Hanto");
		}
		else if (from == null){
			placePiece(to, pieceType);
		}
		else if (to == null){
			throw new HantoException("Invalid move: trying to remove piece from board"); 
		}
		else {
			movePiece(from, to, pieceType);
		}
		
		// Change the boardState after the move

		boardState = boardState.nextMove(moveResult, maxMove);
		
		moveResult = makeMoveResult();

		moveCounter++;
	
	}
	/**
	 * Generate a current move result
	 * @return
	 */
	private MoveResult makeMoveResult() {
		boolean blueWin = false, redWin = false;
		findButterflyHexes();
		if (blueButterflyHex != null){
			redWin = hasPieceAt(blueButterflyHex.makeRelativeCoordinate(UP))
				&& hasPieceAt(blueButterflyHex.makeRelativeCoordinate(DOWN))
				&& hasPieceAt(blueButterflyHex.makeRelativeCoordinate(UPRIGHT))
				&& hasPieceAt(blueButterflyHex.makeRelativeCoordinate(UPLEFT))
				&& hasPieceAt(blueButterflyHex.makeRelativeCoordinate(DOWNLEFT))
				&& hasPieceAt(blueButterflyHex.makeRelativeCoordinate(DOWNRIGHT));
		}

		if (redButterflyHex != null){
			blueWin = hasPieceAt(redButterflyHex.makeRelativeCoordinate(UP))
					&& hasPieceAt(redButterflyHex.makeRelativeCoordinate(DOWN))
					&& hasPieceAt(redButterflyHex.makeRelativeCoordinate(UPRIGHT))
					&& hasPieceAt(redButterflyHex.makeRelativeCoordinate(UPLEFT))
					&& hasPieceAt(redButterflyHex.makeRelativeCoordinate(DOWNLEFT))
					&& hasPieceAt(redButterflyHex.makeRelativeCoordinate(DOWNRIGHT));
		}

		if (blueWin || redWin) boardState = new GameOver();
		
		return (blueWin && redWin)? DRAW 
				:blueWin ? BLUE_WINS
				:redWin ? RED_WINS 
				:boardState.isGameOver() ? DRAW:OK; 
	}
	
	private MoveResult makeMoveResult(MoveResult mr) {
		boardState = new GameOver();
		return mr;
	}
	
	/**
	 * query for current butterfly position
	 */
	private void findButterflyHexes() {
		for (HantoCoordinateImpl key:coordinateTable.keySet()){
			if (coordinateTable.get(key).getType() == BUTTERFLY){
				if (coordinateTable.get(key).getColor() == BLUE) {
					blueButterflyHex = key;
				}
				else if (coordinateTable.get(key).getColor() == RED){
					redButterflyHex = key;
				}
			}
		}
	}

	/**
	 * Getter for move result
	 */
	public MoveResult getMoveResult(){
		return moveResult;
	}
	
	/**
	 * Check if a hex has piece
	 * @param where
	
	 * @return a boolean indicates if a position has piece on it */
	public boolean hasPieceAt(HantoCoordinateImpl where) {
		return getPieceAt(where) != null;
	}
	
	/**
	 * Get the piece object when given coordinate information
	 * @param where
	
	 * @return a */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		final HantoCoordinateImpl coordinate = new HantoCoordinateImpl(where);
		HantoPiece result = null;
		result = coordinateTable.get(coordinate);
		return result;
	}
	/**
	 * a
	
	 * @throws HantoException */
	public void resign() throws HantoException{
		if (!allowResign) {
			throw new HantoException("Invalid move: resign is not allowed");
		}
		if (boardState.currentColor() == RED) {
			moveResult = makeMoveResult(BLUE_WINS);
		}
		else moveResult = makeMoveResult(RED_WINS);
	}
	

/**
 * 
 * @param to
 * @param pieceType
 * @throws HantoException 
 */
public void placePiece(HantoCoordinateImpl to, HantoPieceType pieceType) throws HantoException{
		if (to==null) {
			throw new HantoException("Invalid Placement");
		}
		
		if (moveCounter  == 0) { //first move
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("First placement is not to origin");
			}
		}
		
		HantoPieceImpl piece = new HantoPieceImpl(boardState.currentColor(),pieceType);
		
		
		
		PlaceValidator validator = new PlaceValidator(coordinateTable,boardState.currentColor(),moveCounter, diffColorNearby);
		

		if (!isPieceNumberValid(pieceType, boardState.currentColor())){
			throw new HantoException("Number of the piece out of limit");
		}
		
		ButterFlyValidator butterFlyValidator = new ButterFlyValidator(coordinateTable,maxButterflyTurns);
		
		if (!butterFlyValidator.butterflyPlacedBeofreRequiredMove(boardState.currentColor(), moveCounter)) {
			throw new HantoException("Butterfly not placed before required moves");
		}
		
		
		if (validator.canPlace(piece, to)) {
			coordinateTable.put(to, piece);
			final ConnectionValidator connectionValidator = new ConnectionValidator(coordinateTable);
			if (!connectionValidator.isConnectedGraph()) {
				throw new HantoException("Can not place piece to non-adjacent point");
			}
		}
		else {
			throw new HantoException("Can not place piece to the target point");
		}
	}
	/**
	 * @param from
	 * @param to
	 * @param pieceType
	 * @throws HantoException */
	public void movePiece(HantoCoordinateImpl from, HantoCoordinateImpl to, HantoPieceType pieceType) throws HantoException{
		if (getPieceAt(from) == null) {
			throw new HantoException("Moving from empty hex");
		}
		
		if (getPieceAt(from).getType() != pieceType) {
			throw new HantoException("You cannot use wrong type");
		}
		
		if (boardState.currentColor() != getPieceAt(from).getColor()){
			throw new HantoException("You cannot use wrong color");
		}
		
		HantoPieceImpl piece = coordinateTable.get(from);
		ButterFlyValidator butterFlyValidator = new ButterFlyValidator(coordinateTable, maxButterflyTurns);
		// generate the validator
		final MoveRule rule = ruleSet.get(pieceType);
		MoveValidatorStrategy validator = makeValidator(rule.getMoveType(), rule.getMaxDistance());
		
		if (!butterFlyValidator.butterflyPlaced(boardState.currentColor())){
			validator = makeValidator(HantoMoveType.STILL, 0);
		}
		
		if (!validator.canMove(from, to)) {
			throw new HantoException("There's no valid way to make this move");
		}
		else {
			coordinateTable.remove(from);
			coordinateTable.put(to, piece);
		}
	}
	
	/**
	 * A factory method that provides the move strategy
	 * @param moveType
	 * @return
	 */
	private MoveValidatorStrategy makeValidator(HantoMoveType moveType, int maxDistance) {
		switch (moveType) {
		case WALK:
			return new WalkValidator(maxDistance,coordinateTable);
		case FLY:
			return new FlyValidator(coordinateTable);
		case STILL:
			return new StillValidator();
		case JUMP:
			return new JumpValidator(coordinateTable);
		default:
			break;
		}
		return null;
	}
	
	/**
	 * validate if a type has valid number of pieces
	 */
	private boolean isPieceNumberValid(HantoPieceType type,HantoPlayerColor color){
		final int max = ruleSet.get(type).getMaxNum();
		int acc = 0;//accumulator
		for (HantoCoordinateImpl key:coordinateTable.keySet()){
			if ((coordinateTable.get(key).getColor()==color) 
				&& (coordinateTable.get(key).getType()==type)){
				acc++;
			}
		}
		return acc < max;
	}
	
	public void enableDiffColorPlacement(){
		diffColorNearby = true;
	}
}
