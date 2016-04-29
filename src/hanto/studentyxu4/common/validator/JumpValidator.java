package hanto.studentyxu4.common.validator;

import java.util.Map;

import hanto.common.HantoException;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;

public class JumpValidator extends CoordinateValidator implements MoveValidatorStrategy {

	public JumpValidator(Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable) {
		super(coordinateTable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(HantoCoordinateImpl from, HantoCoordinateImpl to) throws HantoException {
		// TODO Auto-generated method stub
		return false;
	}

}
