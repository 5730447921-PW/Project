package logic;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import render.IRenderable;
import render.Screen;
import utility.InputUtility;

public class Player implements IRenderable{
	private int x,y,speed;
	//private boolean outOfFrame;
	private Screen s;
	private static BufferedImage image = null;
	static{
		try{
			image = ImageIO.read(Screen.class.getClassLoader().getResource("res/image/player.png"));
		}
		catch(IOException e){
			
		}
	}
	public Player(Screen s) {
		this.s = s;
	}
	public void move(){
		if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)){
			x-=speed;
			if(x<0){
				x=0;
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)){
			x=+speed;
			if(x<s.getWidth()){
				x=s.getWidth();
			}
		}
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1000;
	}
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(image, null, x, y);
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
