package logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import render.IRenderable;
import utility.RandomUtility;

public class Item extends Fruits implements IRenderable,Collectible {
	
	

	public Item(BufferedImage img,boolean isInCondition,int speed) {
		super(RandomUtility.random(0,1024-img.getWidth()),0-img.getHeight(),0,false,false);
		// TODO Auto-generated constructor stub
		this.speed = speed;
		this.img = img;
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

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(!isDestroy || y <= 460){
			y += speed;
		}
	}

}
