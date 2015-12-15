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
		if(this.time - time >0){
			this.time -= time;
		}
		else this.time = 0;
	}
	
	

}
