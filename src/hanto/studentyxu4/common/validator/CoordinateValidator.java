/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import static hanto.studentyxu4.common.AdjacentPosition.*;

import java.util.Hashtable;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentyxu4.common.AdjacentPosition;
import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;

/**
 * Abstract validator that uses Hanto coordinate
 * @author Yang Xu 
 */
public abstract class CoordinateValidator {
	
	protected Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable = 
			new Hashtable<HantoCoordinateImpl, HantoPieceImpl>();
	
	/**
	 * The default constructor for coordinate validator
	 * @param coordinateTable 
	 */
	protected CoordinateValidator(Map<HantoCoordinateImpl, HantoPieceImpl> coordinateTable){
		this.coordinateTable = coordinateTable;
	}
	
	/**
	 * Get Piece at the given coordinate
	 * @param where
	
	 * @return a Hanto Piece */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		final HantoCoordinateImpl coordinate = new HantoCoordinateImpl(where);
		HantoPiece result = null;
		result = coordinateTable.get(coordinate);
		return result;
	}
	
	/**
	 * Check if a hex has piece
	 * @param where
	 * @return a boolean indicates if a position has piece on it */
	public boolean hasPieceAt(HantoCoordinateImpl where) {
		return getPieceAt(where) != null;
	}
	
	/**
	 * Make a new coordinate table by implementing one step of piece movement
	 * @param from
	 * @param to
	 * @return a new table including the information about updated map  
	 */
	protected Map<HantoCoordinateImpl, HantoPieceImpl> updateTempCoordinate
	(HantoCoordinateImpl from, HantoCoordinateImpl to) {
		Map<HantoCoordinateImpl, HantoPieceImpl> updatedCoordinate;
		updatedCoordinate =  new Hashtable<HantoCoordinateImpl, HantoPieceImpl>(coordinateTable);
		HantoPieceImpl temp = updatedCoordinate.get(from);

		updatedCoordinate.remove(from);

		updatedCoordinate.put(to, temp);
		return updatedCoordinate;
	}
	
	/**
	 * Return AdjacentPostion type by giving index
	 * @param index
	 * @return AdjacentPosition
	 */
	public static AdjacentPosition getAdjacentByIndex(int index){
		switch (index) {
		case 0:
			return UP;
		case 1:
			return UPRIGHT;
		case 5:
			return UPLEFT;
		case 3:
			return DOWN;
		case 4:
			return DOWNLEFT;
		case 2:
			return DOWNRIGHT;
		default:
			return null;
		}
	}
	
	/**
	 * The direct distance between to hexes
	 * @param from hex coordinate 1
	 * @param to hex coordinate 2
	 * @return a int indicates the number of distance difference between 2 hexes
	 */
	protected int DirectDistance(HantoCoordinateImpl from, HantoCoordinateImpl to){
		final int xDif = Math.abs(from.getX() - to.getX());
		final int yDif = Math.abs(from.getY() - to.getY());
		
		return xDif > yDif ? xDif : yDif;
	}
}
