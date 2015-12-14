package logic;

import java.util.ArrayList;

import render.Screen;
import render.StatusBar;
import utility.RandomUtility;

public class GameLogic {

	public Player player;
	public Status status;
	public StatusBar statusBar;
	public ArrayList<Fruits> fruits = new ArrayList<>();

	public GameLogic() {
		player = new Player();
		status = new Status();
		statusBar = new StatusBar();
	}

	public void gameUpdate() {
		createNewFruit();
		moveFruits();
		checkFruitsOut();
		removeFruits();
	}

	public void createNewFruit() {
		int random = RandomUtility.random(0, 4);
		if (random == 0) {
			fruits.add(new Apple(false, 10));
		} else if (random == 1) {
			fruits.add(new Banana(false, 10));
		} else if (random == 2) {
			fruits.add(new Cherry(false, 10));
		} else if (random == 3) {
			fruits.add(new Orange(false, 10));
		} else if (random == 4) {
			fruits.add(new Strawberry(false, 10));
		}
	}

	public void moveFruits() {
		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).move();
		}
	}

	public void checkFruitsOut() {
		for (int i = 0; i < fruits.size(); i++) {
			if(fruits.get(i).y>Screen.screenHeight){
				fruits.get(i).isDestroy = true;
			}
		}
	}
	public void removeFruits(){
		for (int i = 0; i < fruits.size(); i++) {
			if(fruits.get(i).isDestroy){
				fruits.remove(i);
				i--;
			}
		}
	}

}
