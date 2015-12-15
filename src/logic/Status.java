package logic;

public class Status {

	public static int time;
	public static int score;

	public Status() {
		score = 0;
		time = 10;

		
	}

	public void addScore(int score) {
		if (this.score + score > 0) {
			this.score += score;
		} else
			this.score = 0;

	}

	public void changeTime(int time) {
		if (this.time - time > 0) {
			this.time -= time;
		} else
			this.time = 0;
	}

}
