package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.*;
import utility.InputUtility;

public class Screen extends JPanel {
	public static int screenWidth=1024,screenHeight=512;
	
	private GameLogic gl;
	public static ClassLoader load = Screen.class.getClassLoader();
	private static BufferedImage image;
	static{
		try{
			image = ImageIO.read(load.getResource("res/image/bg3.png"));
			
		}
		catch(IOException e){
			System.out.println("test");
		}
	}
	
	public static Font largefont = new Font("Tahoma",Font.BOLD,0);
	public static Font smallfont = new Font("Tahoma",Font.BOLD,0);
	static{
		try {
			largefont = Font.createFont(Font.TRUETYPE_FONT,new File(load.getResource("res/font/Arabica/Arabica file/Arabica.ttf").toURI()));
			largefont = largefont.deriveFont(Font.BOLD, 70);
			smallfont = largefont.deriveFont(Font.BOLD, 40);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Screen(GameLogic gl){
		this.gl=gl;
		
		this.setPreferredSize(new Dimension(screenWidth	,screenHeight));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, null, 0, 0);
		g2d.setFont(largefont);
		FontMetrics met = g2d.getFontMetrics();
		double w = met.getStringBounds("PRESS 'SPACE' TO START",g2d).getWidth();
		double h = met.getHeight();
		g2d.setColor(Color.BLACK);
		if(!gl.start){
			g2d.drawString("PRESS 'SPACE' TO START",(int)(512-w/2),(int)(256+h/2)-100);
			}
		
		
		
		for (int i = 0; i < gl.fruits.size(); i++) {
			gl.fruits.get(i).draw(g2d);
		}
		gl.player.draw(g2d);
		
		
		
	}
	
	
	

}
