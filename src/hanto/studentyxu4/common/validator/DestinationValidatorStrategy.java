/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;
/**
 * 
 * @author yx
 * 
 */
public interface DestinationValidatorStrategy {
	
	/**
	 * Method isTargetValid.
	 * @param piece HantoPieceImpl
	 * @param to HantoCoordinateImpl
	 * @return boolean
	 */
	boolean isTargetValid(HantoPieceImpl piece, HantoCoordinateImpl to);
	
}
