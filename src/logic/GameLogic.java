package logic;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.applet.Applet;
import java.applet.AudioClip;

import render.Screen;
import render.StatusBar;
import utility.InputUtility;
import utility.RandomUtility;

public class GameLogic {
	public static boolean start = false;
	public Player player;
	public Status status;
	public StatusBar statusBar;
	public ArrayList<Fruits> fruits = new ArrayList<>();
	private int delay = 0;
	private int delayitem = 0;
	private static AudioClip coin, soundplay, soundopen;
	public static BufferedImage imga, imgb, imgc, imgo, imgs,imgi;
	public boolean conditon[]=new boolean[5];
	public boolean itemwork = false;
	public boolean gameover = false;
	private boolean soundopenPlaying = false;
	private boolean soundplayPlaying = false;
	private static ClassLoader load = GameLogic.class.getClassLoader();
	static {
		try {
			imga = ImageIO.read(load.getResource("res/image/apple.png"));
			imgb = ImageIO.read(load.getResource("res/image/banana.png"));
			imgc = ImageIO.read(load.getResource("res/image/cherry.jpg"));
			imgo = ImageIO.read(load.getResource("res/image/orange.png"));
			imgs = ImageIO.read(load.getResource("res/image/strawberry.png"));
			imgi = ImageIO.read(load.getResource("res/image/item.png"));
			coin = Applet.newAudioClip(load.getResource("res/sound/smw_coin.wav").toURI().toURL());
			soundplay = Applet.newAudioClip(
					load.getResource("res/sound/musica-alegre-harpa-judaica-palhaco-circo.wav").toURI().toURL());
			soundopen = Applet.newAudioClip(
					load.getResource("res/sound/LA_MARCHE_DES_PETITS_ROBOTS_INSTRUMENTAL_converted.wav").toURI().toURL());

		} catch (IOException e) {

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GameLogic() {
		player = new Player();
		status = new Status();
		
		conditon = condition(conditon);
		statusBar = new StatusBar(status,this);
		soundopen.loop();
		soundopenPlaying=true;
	}

	public void gameUpdate() {
		if(gameover){
			return;
		}
		if (!start) {
			
		} else {
			if(soundopenPlaying){
			soundopen.stop();
			soundopenPlaying=false;
			}
			if(!soundplayPlaying){
				soundplay.loop();
				soundplayPlaying=true;
			}
			if(delayitem == 150){
				itemwork = false;
				delayitem = 0;
			}
			if (delay == 10) {
				createNewFruit();
				delay = 0;

			}

			moveFruits();
			int pointxPlayer = player.getX() + (int) (player.getImage().getWidth() / 2);

			for (int i = 0; i < fruits.size(); i++) {
				int pointxfruits = (int) (fruits.get(i).x + fruits.get(i).img.getWidth() / 2);
				double max = Math.hypot((player.getImage().getWidth() / 2 + fruits.get(i).img.getWidth() / 2),
						fruits.get(i).img.getHeight());
				if (Math.abs(pointxPlayer - pointxfruits) < (player.getImage().getWidth() / 2
						+ fruits.get(i).img.getWidth() / 2)
						&& Math.hypot(player.getImage().getWidth() / 2 + fruits.get(i).img.getWidth() / 2,
								Math.abs(player.getY() - fruits.get(i).y)) < max
						&& fruits.get(i).y < player.getY()) {
					status.addScore(fruits.get(i).score);
					if(fruits.get(i) instanceof Apple){
						if(statusBar.fruits[0].num - 1>0)statusBar.fruits[0].num--;
						else statusBar.fruits[0].num = 0;}
					else if(fruits.get(i) instanceof Banana){
						if(statusBar.fruits[1].num - 1>0) statusBar.fruits[1].num--;
						else statusBar.fruits[1].num = 0;}
					else if(fruits.get(i) instanceof Cherry){
						if(statusBar.fruits[2].num - 1>0) statusBar.fruits[2].num--;
						else statusBar.fruits[2].num = 0;}
					else if(fruits.get(i) instanceof Orange){
						if(statusBar.fruits[3].num - 1>0) statusBar.fruits[3].num--;
						else statusBar.fruits[3].num = 0;}
					else if(fruits.get(i) instanceof Strawberry){
						if(statusBar.fruits[4].num - 1>0) statusBar.fruits[4].num--;
						else statusBar.fruits[4].num = 0;}
					if(fruits.get(i) instanceof Item){
						itemwork = true;
						
					}
					
					coin.play();
					fruits.get(i).isDestroy = true;
				}
			}
			if(itemwork){
				delayitem++;
			}
			checkFruitsOut();
			

			delay++;
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE)) {
			start = true;
			synchronized (status) {
				status.notifyAll();
			}
		}
		int checknum=0;
		for(int i = 0; i < conditon.length; i++){
			if(conditon[i]){
				checknum += statusBar.fruits[i].num;
			}
		}
		if(status.time == 0 || checknum == 0){
			gameover = true;
		}

	}

	public void createNewFruit() {
		int random = RandomUtility.random(0, 97);
		if(!itemwork){
		if (random >=0 && random <19) {
			fruits.add(new Apple(imga, conditon[0], RandomUtility.random(5, 10)));
		} else if (random >= 19 && random <38) {
			fruits.add(new Banana(imgb, conditon[1], RandomUtility.random(5, 10)));
		} else if (random >= 38 && random <57) {
			fruits.add(new Cherry(imgc, conditon[2], RandomUtility.random(5, 10)));
		} else if (random >= 57 && random < 76) {
			fruits.add(new Orange(imgo, conditon[3], RandomUtility.random(5, 10)));
		} else if (random >= 76 && random < 95) {
			fruits.add(new Strawberry(imgs, conditon[4], RandomUtility.random(5, 10)));
		} else if (random>= 95 && random<98){
			fruits.add(new Item(imgi, false, RandomUtility.random(5, 10)));
		}
		}
		else{
			for(int i = 0;i<conditon.length;i++){
				if(conditon[i]){
					if(i == 0) fruits.add(new Apple(imga, conditon[0], RandomUtility.random(5, 10)));
					else if(i == 1) fruits.add(new Banana(imgb, conditon[1], RandomUtility.random(5, 10)));
					else if(i == 2) fruits.add(new Cherry(imgc, conditon[2], RandomUtility.random(5, 10)));
					else if (i == 3) fruits.add(new Orange(imgo, conditon[3], RandomUtility.random(5, 10)));
					else if(i == 4) fruits.add(new Strawberry(imgs, conditon[4], RandomUtility.random(5, 10)));
				}
			}
		}
	}

	public void moveFruits() {
		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).move();
			

		}
	}

	public void checkFruitsOut() {
		for (int i = 0; i < fruits.size(); i++) {
			if (fruits.get(i).y > 512 - fruits.get(i).img.getHeight() - 26) {
				fruits.get(i).isDestroy = true;
			}
			if (fruits.get(i).isDestroy) {
				fruits.remove(i);
				i--;
			}
		}
	}
	
	public boolean[] condition(boolean[] condition){
		for (boolean b : condition) {
			b=false;
		}
		int a = RandomUtility.random(0, 4);
		int c = RandomUtility.random(0, 4);
		condition[a] = true;
		condition[c] =true;
		return condition;
	}
	

}
