package utility;

public class RandomUtility {
	
	public static int random(int x, int y){
		int a = (int)(Math.random() * (y-x + 1)) + x; 
		return a;
	}

}
