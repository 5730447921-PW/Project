package logic;

import render.Screen;

public class GameLogic {
	
	protected Player player;
	protected Status status;
	protected Screen screen;
	

	public GameLogic() {
		screen = new Screen();
		player = new Player(screen);
		status = new Status();
	}
	
	
	
	

}
