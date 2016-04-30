/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import java.util.Hashtable;
import java.util.Map;

import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;

/**
 * Validator for flying.
 * @author yx
 *
 */
public class FlyValidator extends CoordinateValidator implements MoveValidatorStrategy{

	private int maxDistance = Integer.MAX_VALUE;
	
	/**
	 * Constructor for FlyValidator.
	 * @param distance
	 * @param coordinateTable Map<HantoCoordinateImpl,HantoPieceImpl>
	 */
	public FlyValidator(int distance, Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable) {
		super(coordinateTable);
		maxDistance = distance;
	}

	@Override
	public boolean canMove(HantoCoordinateImpl from, HantoCoordinateImpl to) {
		if (hasPieceAt(to)) {
			return false;
		}
		
		if (directDistance(from, to) > maxDistance) {
			return false;
		}
		
		Map<HantoCoordinateImpl, HantoPieceImpl> updatedCoordinate;
		updatedCoordinate = new Hashtable<HantoCoordinateImpl, HantoPieceImpl>(coordinateTable);
		updatedCoordinate = updateTempCoordinate(from, to);
	
		ConnectionValidator connectionValidator = new ConnectionValidator(updatedCoordinate);
		if (!connectionValidator.isConnectedGraph()){
			return false;
		}
	
		return true;
	}
	
	

}
