/**
 * Hanto 2016 
 * Yang Xu
 */
package hanto.studentyxu4.common.validator;

import java.util.Map;

import hanto.common.HantoException;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;

public class JumpValidator extends CoordinateValidator implements MoveValidatorStrategy {
	/**
	 * Default constructor for jump validator
	 * @param coordinateTable
	 */
	public JumpValidator(Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable) {
		super(coordinateTable);
	}

	@Override
	public boolean canMove(HantoCoordinateImpl from, HantoCoordinateImpl to) throws HantoException {
		if (hasPieceAt(to)) {
			return false;
		}
		//validate if source and destination are in a straight line
		final int xDif = to.getX() - from.getX();
		final int yDif = to.getY() - from.getY();
		
		if ((xDif != (-yDif)) && (xDif != 0) && (yDif != 0)) {
			return false;
		}
		//validate if path is continuous
		final int xDir = (xDif == 0) ? 0 : (xDif/Math.abs(xDif));
		final int yDir = (yDif == 0) ? 0 : (yDif/Math.abs(yDif));
		int x = from.getX(), y = from.getY();
		while ((x != to.getX()) || (y != to.getY())) {
			if (!hasPieceAt(new HantoCoordinateImpl(x,y))){
				return false;
			}
			x = x + xDir;
			y = y + yDir;
		}
		
		return true;
	}

}
