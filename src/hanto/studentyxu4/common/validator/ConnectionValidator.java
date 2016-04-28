/**
 *  This file is used for CS4223 in Worcester Polytechnic Institute. 
 */
package hanto.studentyxu4.common.validator;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import hanto.studentyxu4.common.HantoCoordinateImpl;
import hanto.studentyxu4.common.HantoPieceImpl;

import static hanto.studentyxu4.common.AdjacentPosition.*;
/**
 * The main class for connected graph validator
 * @author Yang Xu
 *
 */
public class ConnectionValidator {

	private Map<HantoCoordinateImpl, HantoPieceImpl> hexmap;
	private Hashtable<HantoCoordinateImpl, Integer> hexVisited = 
			new Hashtable<HantoCoordinateImpl, Integer>();
	private int visitedCounter = 0;
	/**
	 * Constructor for the connection validator
	 * @param activeHexes
	 */
	public ConnectionValidator(Map<HantoCoordinateImpl, HantoPieceImpl> activeHexes) {
		hexmap = activeHexes;
		for (HantoCoordinateImpl key:hexmap.keySet()){
			hexVisited.put(key, 0);
		}
	}
	/**
	 * The method to determine if the map  is connected
	 * @return a boolean indicates if this map is connected
	 */
	public boolean isConnectedGraph()
	{
		final int totalKeys = hexVisited.size();
			
		HantoCoordinateImpl currentCoordiante;
		
		Enumeration<HantoCoordinateImpl> coordinateSet = hexVisited.keys();
		
		currentCoordiante = coordinateSet.nextElement();
		findNearbyCoordinate(currentCoordiante);

		return visitedCounter == totalKeys;
		
	}
	/**
	 * Using BFS to traverse the map
	 * @param currentCoordiante
	 */
	private void findNearbyCoordinate(HantoCoordinateImpl currentCoordiante) {
		if (!hexVisited.containsKey(currentCoordiante) || (hexVisited.get(currentCoordiante) == 1)) return;
		hexVisited.replace(currentCoordiante, 1);
		findNearbyCoordinate(currentCoordiante.makeRelativeCoordinate(UP));
		findNearbyCoordinate(currentCoordiante.makeRelativeCoordinate(DOWN));
		findNearbyCoordinate(currentCoordiante.makeRelativeCoordinate(UPLEFT));
		findNearbyCoordinate(currentCoordiante.makeRelativeCoordinate(UPRIGHT));
		findNearbyCoordinate(currentCoordiante.makeRelativeCoordinate(DOWNLEFT));
		findNearbyCoordinate(currentCoordiante.makeRelativeCoordinate(DOWNRIGHT));
		visitedCounter++;
	}
}
