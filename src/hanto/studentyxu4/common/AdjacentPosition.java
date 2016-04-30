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
/**
 * The enum type for the adjacent positions, representing 6 adjacent hexes near a certain hex
 * @author Yang Xu yxu4	
 */
public enum AdjacentPosition {
	UP(0,1,0),
	UPRIGHT(1,0,1),
	UPLEFT(-1,1,2),
	DOWN(0,-1,3),
	DOWNLEFT(-1,0,4),
	DOWNRIGHT(1,-1,5);
	
	private final int x;
	private final int y;
	private AdjacentPosition(int x, int y, int index)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
}
