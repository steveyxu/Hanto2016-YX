/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import static hanto.studentyxu4.common.AdjacentPosition.*;

import java.util.Map;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentyxu4.common.AdjacentPosition;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;
/**
 * The validator for placement move.
 * @author yx
 *
 */
public class PlaceValidator extends CoordinateValidator{
    private HantoPlayerColor color;
	private int moveCounter;
	private boolean diffColorNearby = false;
	
    
    /**
     * Constructor for PlaceValidator.
     * @param coordinateTable Map<HantoCoordinateImpl,HantoPieceImpl>
     * @param color HantoPlayerColor
     * @param moveCounter int
     * @param diffColorNearby boolean true if allow place a piece nearby opponent's color 
     */
    public PlaceValidator(Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable, 
    		HantoPlayerColor color, int moveCounter, boolean diffColorNearby) {
		super(coordinateTable);
		this.color = color;
		this.moveCounter = moveCounter;
		this.diffColorNearby = diffColorNearby;
	}
	
	/**
	 * Method canPlace.
	 * @param piece HantoPieceImpl
	 * @param to HantoCoordinateImpl
	 * @return boolean
	 */
	public boolean canPlace (HantoPieceImpl piece, HantoCoordinateImpl to){
		if (hasPieceAt(to)){
			return false;
		}
		return checkPlaceable(to);
	}
	/**
	 * 
	 * @param destination
	 * @return
	 * @throws HantoException
	 */
	private boolean checkPlaceable(final HantoCoordinateImpl destination){
		
		final boolean hasAllSameColorNearby = diffColorNearby || (sameColorAdjacentHex(destination, UP)
								&& sameColorAdjacentHex(destination, DOWN)
								&& sameColorAdjacentHex(destination, UPLEFT)
								&& sameColorAdjacentHex(destination, UPRIGHT)
								&& sameColorAdjacentHex(destination, DOWNLEFT)
								&& sameColorAdjacentHex(destination, DOWNRIGHT));
		
		if ((!hasAllSameColorNearby) && (moveCounter > 1)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if a certain nearby hex are the same color 
	 * @param coordinate the current coordinate
	 * @param position the relative position between the adjacent piece and current piece
	 * @see hanto.studentyxu4.common.AdjacentPosition
	 * @return true if the color matches
	 */
	private boolean sameColorAdjacentHex(final HantoCoordinateImpl coordinate,
			final AdjacentPosition position) 
	{
		final HantoPlayerColor currentColor = getCurrentColor();
		final HantoCoordinateImpl newCoordinate = coordinate.makeRelativeCoordinate(position);
		final HantoPiece thisPiece = getPieceAt(newCoordinate);
		return thisPiece == null ? true : thisPiece.getColor() == currentColor;
	}
	
	/**
	 * Get the color for the current move
	 * @return
	 */
	private HantoPlayerColor getCurrentColor() {
		return color;
	}


}
