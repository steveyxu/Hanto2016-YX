/**
 * 
 */
package hanto.studentyxu4.common.validator;

import java.util.Hashtable;
import java.util.Map;

import hanto.common.HantoException;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;

/**
 * @author steve
 *
 */
public class WalkValidator extends CoordinateValidator implements MoveValidatorStrategy {
	private int maxDistance;
	
	/**
	 * Constructor for WalkValidator.
	 * @param maxDistance int
	 * @param coordinateTable Map<HantoCoordinateImpl,HantoPieceImpl>
	 */
	public WalkValidator(int maxDistance,
			Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable) {
		super(coordinateTable);
		this.maxDistance = maxDistance;
	}

	/*
	 * @see hanto.studentyxu4.common.validator.MoveValidatorStrategy#canMove(hanto.studentyxu4.common.HantoCoordinateImpl, hanto.studentyxu4.common.HantoCoordinateImpl)
	 */
	@Override
	public boolean canMove(HantoCoordinateImpl from, HantoCoordinateImpl to) throws HantoException {

		//check if destination is available
		if (getPieceAt(to) != null) {
			throw new HantoException("Destination is not empty");
		}
		//check if piece exists
		HantoPieceImpl thisPiece = coordinateTable.get(from);
		if (thisPiece == null) {
			throw new HantoException("The piece does not exist");
		}
		//check for the moves in allowed steps
		boolean result = findPath(from, to, 0);
		return result;
	}
	
	/**
	 * Method findPath.
	 * @param from HantoCoordinateImpl
	 * @param to HantoCoordinateImpl
	 * @param counter int
	 * @return boolean
	 */
	public boolean findPath(final HantoCoordinateImpl from, 
			final HantoCoordinateImpl to, int counter){
		if (from.equals(to)) {
			return true;
		}
		
		if (counter >= maxDistance) {
			return false;
		}
		
		
		boolean[] nearbyHexes = new boolean[6];
		for (int i=0;i < 6;i++){
			nearbyHexes[i] = hasPieceAt(from.makeRelativeCoordinate(getAdjacentByIndex(i)));
		}
		
		boolean result = false;
		Map<HantoCoordinateImpl, HantoPieceImpl> updatedCoordinate;

		for (int i=0;i < 6;i++){
			updatedCoordinate = new Hashtable<HantoCoordinateImpl, HantoPieceImpl>(coordinateTable);
			
			HantoCoordinateImpl target = from.makeRelativeCoordinate(getAdjacentByIndex(i));
						
			boolean canNotMoveToTraget = (nearbyHexes[(i + 5) % 6] 
					&& nearbyHexes[(i + 1) % 6])
					|| nearbyHexes[i];
	
			if (!canNotMoveToTraget){
				updatedCoordinate = updateTempCoordinate(from, target);
				
				ConnectionValidator connectionValidator = new ConnectionValidator(updatedCoordinate);
				
				WalkValidator tempWalkValidator = new WalkValidator(maxDistance, updatedCoordinate);
				
				if (connectionValidator.isConnectedGraph()){
					result = result || (tempWalkValidator.findPath(target, to, counter + 1));
					if (result) return result;
				}
			}
		}
		return result;
	}



}
