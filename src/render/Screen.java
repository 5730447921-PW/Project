package render;

import java.awt.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import input.InputUtility;

public class Screen extends JPanel {
	
	static ClassLoader load = Screen.class.getClassLoader();
	private static BufferedImage image;
	static{
		try{
			
			image = ImageIO.read(load.getResource("res/image/bg3.png"));
		}
		catch(IOException e){
			System.out.println("test");
		}
	}
	
	public Screen(){

		this.setPreferredSize(new Dimension(1024,512));
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					InputUtility.setKeyPressed(e.getKeyCode(),false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					InputUtility.setKeyPressed(e.getKeyCode(),true);
				}
			}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, null, 0, 0);
		
		Font font = new Font("Tahoma",Font.BOLD,0);//******
				try {
					font = Font.createFont(Font.TRUETYPE_FONT,new File(load.getResource("res/font/Arabica/Arabica file/Arabica.ttf").toURI()));
					font = font.deriveFont(Font.BOLD, 70);
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
		
		g2d.setFont(font);
		FontMetrics met = g2d.getFontMetrics();
		double w = met.getStringBounds("PRESS 'SPACE' TO START",g2d).getWidth();
		double h = met.getHeight();
		g2d.setColor(Color.BLACK);
		g2d.drawString("PRESS 'SPACE' TO START",(int)(512-w/2),(int)(256+h/2)-100);
		
		
	}
	

}
