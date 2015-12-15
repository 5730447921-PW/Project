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
	public boolean start = false;
	public Player player;
	public Status status;
	public StatusBar statusBar;
	public ArrayList<Fruits> fruits = new ArrayList<>();
	private int delay = 0;
	private static AudioClip coin, soundplay, soundopen,sound;
	private static BufferedImage imga, imgb, imgc, imgo, imgs;

	static {
		try {
			imga = ImageIO.read(Screen.load.getResource("res/image/apple.png"));
			imgb = ImageIO.read(Screen.load.getResource("res/image/banana.png"));
			imgc = ImageIO.read(Screen.load.getResource("res/image/cherry.jpg"));
			imgo = ImageIO.read(Screen.load.getResource("res/image/orange.png"));
			imgs = ImageIO.read(Screen.load.getResource("res/image/strawberry.png"));
			coin = Applet.newAudioClip(Screen.load.getResource("res/sound/smw_coin.wav").toURI().toURL());
			soundplay = Applet.newAudioClip(
					Screen.load.getResource("res/sound/musica-alegre-harpa-judaica-palhaco-circo.wav").toURI().toURL());
			soundopen = Applet.newAudioClip(
					Screen.load.getResource("res/sound/LA_MARCHE_DES_PETITS_ROBOTS_INSTRUMENTAL_converted.wav").toURI().toURL());

		} catch (IOException e) {

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GameLogic() {
		player = new Player();
		status = new Status();
		statusBar = new StatusBar(status);
	}

	public void gameUpdate() {
		if (!start) {
			sound = soundopen;
			sound.play();
			System.out.println(start);
		} else {
			
			sound=soundplay;
			sound.play();
			if (delay == 30) {
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
					coin.play();
					fruits.get(i).isDestroy = true;
				}
			}
			checkFruitsOut();

			delay++;
		}
		if (InputUtility.getKeyPressed(KeyEvent.VK_SPACE)) {
			start = true;
		}

	}

	public void createNewFruit() {
		int random = RandomUtility.random(0, 4);
		if (random == 0) {
			fruits.add(new Apple(imga, false, RandomUtility.random(5, 10)));
		} else if (random == 1) {
			fruits.add(new Banana(imgb, false, RandomUtility.random(5, 10)));
		} else if (random == 2) {
			fruits.add(new Cherry(imgc, false, RandomUtility.random(5, 10)));
		} else if (random == 3) {
			fruits.add(new Orange(imgo, false, RandomUtility.random(5, 10)));
		} else if (random == 4) {
			fruits.add(new Strawberry(imgs, false, RandomUtility.random(5, 10)));
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

}
