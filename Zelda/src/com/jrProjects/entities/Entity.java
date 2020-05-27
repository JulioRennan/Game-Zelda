package com.jrProjects.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jrProjects.graficos.Game;
import com.jrProjects.word.Camera;

public class Entity {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	public static BufferedImage LIFEPACK = Game.spriteSheet.getSprite(6*16, 0,	16, 16);
	public static BufferedImage WEAPON = Game.spriteSheet.getSprite(7*16, 0,	16, 16);
	public static BufferedImage BULLET = Game.spriteSheet.getSprite(6*16, 16,	16, 16);
	public static BufferedImage ENEMY = Game.spriteSheet.getSprite(7*16, 16,	16, 16);
	public Entity(int x, int y, int width, int height,BufferedImage sprite) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void render(Graphics g) {
		g.drawImage(sprite	, getX() - Camera.x, getY()-Camera.y,null);
	}
	public void tick() {
		
	}

}
