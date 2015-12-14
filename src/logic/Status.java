package logic;

public class Status {
	
	public static int time;
	public static int score;
	
	public Status(){
		score = 0;
		time = 100;
	}
	
	public void addScore(int score){
		this.score += score;
		
	}
	
	public void changeTime(int time){
		this.time -= time;
	}
	
	

}
