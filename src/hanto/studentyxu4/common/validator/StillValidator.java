/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import hanto.studentyxu4.common.HantoCoordinateImpl;
/**
 * The strategy for still pieces.
 * @author yx
 *
 */
public class StillValidator implements MoveValidatorStrategy {

	@Override
	public boolean canMove(HantoCoordinateImpl from, HantoCoordinateImpl to) {
		return false;
	}
	
}
