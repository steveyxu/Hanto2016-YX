/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import static hanto.common.HantoPieceType.BUTTERFLY;

import java.util.Map;

import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;
/**
 * The validator to check butterfly status
 * @author yx
 *
 */
public class ButterFlyValidator extends CoordinateValidator {
	int maxTurns;
	
	/**
	 * The constructor for Butterfly Validator
	 * @param coordinateTable is the
	 * @param maxTurns is the limit of turns for a hanto game
	 */
	public ButterFlyValidator(Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable, 
			int maxTurns)
	{
		super(coordinateTable);
		this.maxTurns = maxTurns;
	}
	
	/**
	 * Indicates if the butterfly is placed for the given color
	 * @param color current player color
	 * @return true if the butter fly with this color is placed
	 */
	public boolean butterflyPlaced(HantoPlayerColor color){
		for (HantoCoordinateImpl key:coordinateTable.keySet()){
			if ((coordinateTable.get(key).getColor() == color) 
					&& (coordinateTable.get(key).getType() == BUTTERFLY)) {
				return true;
			}
				
		}
		return false;
	}
	
	/**
	 * Indicates if the butterfly is placed before given moves 
	 * @param color the butterfly color 
	 * @param move the current number of moves
	 * @return true if the butterfly has been placed
	 */
	public boolean butterflyPlacedBeofreRequiredMove(HantoPlayerColor color, int move){
		return ((butterflyPlaced(color)) || ((move / 2) < maxTurns));
	}
}
