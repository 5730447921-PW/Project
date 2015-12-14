package render;

import java.applet.Applet;
import java.applet.AudioClip;
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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.InputUtility;

public class Screen extends JPanel {
	private boolean start = false;
	static ClassLoader load = Screen.class.getClassLoader();
	private static BufferedImage image;
	private static AudioClip sound ;
	static{
		try{
			image = ImageIO.read(load.getResource("res/image/bg3.png"));
			sound = Applet.newAudioClip(load.getResource("res/sound/LA_MARCHE_DES_PETITS_ROBOTS_INSTRUMENTAL.wav").toURI().toURL());
		}
		catch(IOException | URISyntaxException e){
			System.out.println("test");
		}
	}
	
	public static Font largefont = new Font("Tahoma",Font.BOLD,0);
	public static Font smallfont = new Font("Tahoma",Font.BOLD,0);
	static{
		try {
			largefont = Font.createFont(Font.TRUETYPE_FONT,new File(load.getResource("res/font/Arabica/Arabica file/Arabica.ttf").toURI()));
			largefont = largefont.deriveFont(Font.BOLD, 70);
			smallfont = largefont.deriveFont(Font.BOLD, 30);
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
	
	public Screen(){
		sound.loop();
		this.setPreferredSize(new Dimension(1024,512));
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
					InputUtility.setKeyPressed(e.getKeyCode(),false);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
					InputUtility.setKeyPressed(e.getKeyCode(),true);
				
			}
		});
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
		if(!start){
		g2d.drawString("PRESS 'SPACE' TO START",(int)(512-w/2),(int)(256+h/2)-100);
		}
		if(InputUtility.getKeyPressed(KeyEvent.VK_SPACE)){
			start = true;
		}
	}
	
	
	

}
