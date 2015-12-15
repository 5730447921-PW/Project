import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import logic.GameLogic;
import logic.Player;
import logic.Status;
import render.Screen;
import render.StatusBar;
import utility.InputUtility;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Speial Fruit");
		f.setLayout(new BorderLayout());
		f.setPreferredSize(new Dimension(1024,592));
		GameLogic gl = new GameLogic();
		Screen s = new Screen(gl);

		f.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(e.getKeyCode(), true);
			}
		});
		f.add(s,BorderLayout.CENTER);
		f.add(gl.statusBar,BorderLayout.NORTH);
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					
					f.repaint();
					gl.player.move();
					gl.gameUpdate();
					
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

}
