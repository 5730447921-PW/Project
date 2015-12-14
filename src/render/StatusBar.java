package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import logic.Status;

public class StatusBar extends JPanel {
	
	public StatusBar(){
		setPreferredSize(new Dimension(1024,40));
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1024,40);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Screen.smallfont);
		g2d.drawString("Time : " + Status.time,5,35);
		g2d.drawString("Score : " + Status.score,341,35);
	}

}
