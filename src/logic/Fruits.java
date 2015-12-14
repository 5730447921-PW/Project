package logic;

public abstract class Fruits {
	protected int x;
	protected int y;
	protected int speed;
	protected int score;
	protected boolean isInCondition;
	protected boolean isDestroy;

	
	public Fruits(int x, int y, int score, boolean isInCondition, boolean isDestroy){
		this.x = x;
		this.y = y;
		this.isInCondition = isInCondition;
		this.score = score;
		this.isDestroy = isDestroy;
	}
	 
	

}
