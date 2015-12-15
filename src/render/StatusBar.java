package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import logic.Status;

public class StatusBar extends JPanel implements IRenderable {
	private Status status;
	public StatusBar(Status status){
		setPreferredSize(new Dimension(Screen.screenWidth,40));
		this.status = status;
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		this.draw(g2d);
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
		g2d.fillRect(0, 0, getWidth(),40);
		g2d.setColor(Color.WHITE);
		g2d.setFont(Screen.smallfont);
		/*new Thread(new Runnable() {
			public void run() {
				while(true){//********
					try{
						Thread.sleep(1000);
						status.changeTime(1);
					}catch(InterruptedException i){
						
					}
					
				}
			}
		}).start();*/
		g2d.drawString("Time : " + Status.time,5,30);
		g2d.drawString("Score : " + Status.score,341,30);
	}
	
	public boolean isVisible(){
		return true;
	}
	

}
