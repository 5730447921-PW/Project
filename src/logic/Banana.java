package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import render.IRenderable;
import render.Screen;
import utility.RandomUtility;

public class Banana extends Fruits  {
	
	
	public Banana(BufferedImage img,boolean isInCondition,int speed){
		super(RandomUtility.random(0,1024-img.getWidth()),0-img.getHeight(),5,isInCondition,false);
		this.speed = speed;
		this.img = img;
		if(isInCondition) this.num = RandomUtility.random(10,20);
		else this.score = -this.score;
			
		
	}
	

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(!isDestroy || y <= 460){
			y += speed;
		}
		
	}


	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 999;
	}


	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(img, null, x, y);
	}


	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
