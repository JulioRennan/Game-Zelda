package com.jrProjects.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jrProjects.graficos.Game;

public class Tile {
	
	
	public static BufferedImage TILE_GRAMA = Game.spriteSheet.getSprite(0, 0, 16,16);
	public static BufferedImage TILE_PAREDE = Game.spriteSheet.getSprite(16, 0, 16,16);
	
	private BufferedImage sprite;
	private int  x,y;
	
	
	
	public Tile(BufferedImage sprite, int x, int y) {
		super();
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
	
	
	
			
}
