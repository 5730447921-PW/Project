package logic;

public class Apple extends Fruits implements Collectible {
	
	
	
	public Apple(int x, int y ,boolean isInCondition,int speed){
		super(x,y,5,isInCondition,false);
		this.speed = speed;
	}
	

	@Override
	public void move(int speed) {
		// TODO Auto-generated method stub
		if(!isDestroy || y <= 460){
			y += speed;
		}
		
	}

}
