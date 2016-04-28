/**
 * The strategy for move validator 
 * @author Yang Xu
 */
package hanto.studentyxu4.common.validator;

import hanto.common.HantoException;
import hanto.studentyxu4.common.HantoCoordinateImpl;
/**
 * Interface for all the movements
 * @author yx
 *
 */
public interface MoveValidatorStrategy{
	/**
	 * Method canMove. True if the piece can move to target.
	 * @param from HantoCoordinateImpl
	 * @param to HantoCoordinateImpl
	 * @return boolean
	 * @throws HantoException
	 */
	boolean canMove(HantoCoordinateImpl from, HantoCoordinateImpl to) throws HantoException;
}
