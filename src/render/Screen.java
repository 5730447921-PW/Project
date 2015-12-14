package render;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.Graphics;
import com.sun.prism.Image;

public class Screen extends JFrame {
	
	private static BufferedImage image;
	static{
		try{
			ClassLoader load = Screen.class.getClassLoader();
			image = ImageIO.read(load.getResource("res/image/bg3.png"));
		}
		catch(IOException e){
			
		}
	}
	private JPanel p;
	
	public Screen(){
		setTitle("Special Fruits");
		p = new JPanel();
		p.setPreferredSize(new Dimension(1024,512));
		add(p);
		setVisible(true);
	}
	
	public void paintComponent(Graphics2D g2d){
		JLabel label = new JLabel("PRESS 'SPACE'");
		
	}
	

}
