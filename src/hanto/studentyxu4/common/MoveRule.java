/**
 * The class to represent the rule for a piece type
 */
package hanto.studentyxu4.common;
/**
 * 
 * @author Yang Xu
 * 
 */
public class MoveRule {
	private int maxDistance;
	private int maxNum;
	private HantoMoveType moveType;
	
	/**
	 * Default Constructor for move rule
	 * @param maxDistance the max distance of the movement
	 * @param maxNum the max number of pieces that are the same type

	 * @param moveType the type of movement
	 */
	public MoveRule(int maxDistance,int maxNum,HantoMoveType moveType){
		this.maxDistance = maxDistance;
		this.maxNum = maxNum;
		this.moveType = moveType;
	}
	
	public HantoMoveType getMoveType() {
		return moveType;
	}
	
	public int getMaxDistance() {
		return maxDistance;
	}
	
	public int getMaxNum(){
		return maxNum;
	}
	
}
