/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2015 Gary F. Pollice
 *******************************************************************************/

package hanto.studentyxu4.common;

import java.util.ArrayList;
import java.util.Collection;

import hanto.common.HantoCoordinate;

import static hanto.studentyxu4.common.AdjacentPosition.*;

/**
 * The implementation for my version of Hanto.
 * @version Mar 2, 2016
 */
public class HantoCoordinateImpl implements HantoCoordinate
{
	private final int x, y;

	/**
	 * The only constructor.
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public HantoCoordinateImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Copy constructor that creates an instance of HantoCoordinateImpl from an
	 * object that implements HantoCoordinate.
	 * @param coordinate an object that implements the HantoCoordinate interface.
	 */
	public HantoCoordinateImpl(HantoCoordinate coordinate)
	{
		this(coordinate.getX(), coordinate.getY());
	}
	
	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	/* 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		HantoCoordinateImpl other = (HantoCoordinateImpl) obj;
		if (x != other.x){
			return false;
		}
		if (y != other.y){
			return false;
		}
		return true;
	}

	/**
	 * @param position
	 * @return a new coordinate  */
	public HantoCoordinateImpl makeRelativeCoordinate(final AdjacentPosition position) {
		final HantoCoordinateImpl newCoordinate = 
				new HantoCoordinateImpl(x + position.getX(),
						y + position.getY());
		return newCoordinate;
	}
	
	/**
	 * Find nearby hexes of an coordinate
	 * @return Collection<HantoCoordinateImpl>
	 */
	public Collection<HantoCoordinateImpl> getAdjacentCoordinates() {
		Collection<HantoCoordinateImpl> ret = new ArrayList<HantoCoordinateImpl>();
		ret.add(makeRelativeCoordinate(UP));
		ret.add(makeRelativeCoordinate(DOWN));
		ret.add(makeRelativeCoordinate(UPLEFT));
		ret.add(makeRelativeCoordinate(UPRIGHT));
		ret.add(makeRelativeCoordinate(DOWNLEFT));
		ret.add(makeRelativeCoordinate(DOWNRIGHT));
		
		return ret;
	}
	/**
	 * @param to HantoCoordinateImpl
	 * @return the distance to the target coordinate
	 */
	public int distanceTo(HantoCoordinateImpl to) {
		int z1 = 0 - x - y;
		int z2 = 0 - to.getX() - to.getY();

		return (Math.abs(x - to.getX()) + Math.abs(y - to.getY()) + Math.abs(z1 - z2)) / 2;
	}
	

}
