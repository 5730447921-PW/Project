package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import logic.*;
import logic.Cherry;
import logic.Fruits;
import logic.GameLogic;
import logic.Status;

public class StatusBar extends JPanel implements IRenderable {
	private Status status;
	private GameLogic gl;
	public Fruits[] fruits = new Fruits[5];

	public StatusBar(Status status, GameLogic gl) {
		setPreferredSize(new Dimension(Screen.screenWidth, 50));
		this.status = status;
		this.gl = gl;

		fruits[0] = new Apple(gl.imga, gl.conditon[0], 1);
		fruits[1] = new Banana(gl.imgb, gl.conditon[1], 1);
		fruits[2] = new Cherry(gl.imgc, gl.conditon[2], 1);
		fruits[3] = new Orange(gl.imgo, gl.conditon[3], 1);
		fruits[4] = new Strawberry(gl.imgs, gl.conditon[4], 1);
		new Thread(new Runnable() {
			public void run() {
				while (true) {// ********
					if (!GameLogic.start) {
						synchronized (status) {
							try {
								status.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else {
						
						try {
							Thread.sleep(1000);
							status.changeTime(1);

						} catch (InterruptedException i) {

						}
					}

				}
			}
		}).start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		this.draw(g2d);
		int x = 800;
		for (int i = 0; i < gl.conditon.length; i++) {
			if (gl.conditon[i]) {
				g2d.drawImage(fruits[i].img, null, x, 1);
				x += 50;
				g2d.drawString("" + fruits[i].num, x, 40);
				x += 50;
			}
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), 50);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Screen.smallfont);
		g2d.drawString("Time : " + Status.time, 5, 40);
		g2d.drawString("Score : " + Status.score, 300, 40);
		g2d.drawString("Condition : ", 600, 40);

	}

	public boolean isVisible() {
		return true;
	}

}
