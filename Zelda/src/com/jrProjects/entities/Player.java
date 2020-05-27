package com.jrProjects.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jrProjects.graficos.Game;
import com.jrProjects.word.Camera;
import com.jrProjects.word.World;

public class Player extends Entity {
	public boolean right,up,left,down,moved;
	public double speed = 1;
	private BufferedImage[] righPlayer;
	private BufferedImage[] leftPlayer;
	private int dir = 1;
	private int frames = 0,maxFrames = 5,index = 0,maxIndex = 3;
	
	@Override
	public void tick() {
		moved = false;
		if(right) {
			this.x+=speed;
			dir = 1;
			moved = true;
		}else if(left) {
			this.x-=speed;
			dir = 0;
			moved = true;
		
		}
		if(up) {
			this.y-=speed;
			moved = true;
		
		}else if(down) {
			this.y+=speed;
			moved = true;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index>maxIndex) {
					index = 0;
				}
			}
		}else {
			index = 0;
		}

		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		
		

		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
		
		System.out.println("Camera x " + Camera.x+"Pos Y " + getX());
	}
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		righPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		for(int i =0;i<4;i++) {
			righPlayer[i] = Game.spriteSheet.getSprite(32+(i*16), 0, 16, 16);
			leftPlayer[i] = Game.spriteSheet.getSprite(32+(i*16), 16, 16, 16);
		}
		
		
	}
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(dir ==1) {
			g.drawImage(righPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(dir == 0) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		
	}

}
