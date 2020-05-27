package com.jrProjects.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jrProjects.entities.Bullet;
import com.jrProjects.entities.Enemy;
import com.jrProjects.entities.Entity;
import com.jrProjects.entities.Lifepack;
import com.jrProjects.entities.Weapon;
import com.jrProjects.graficos.Game;

public class World {
		
	private Tile[] tiles;
	
	private int grama = 0xFF000000;
	private int parede = 0xFF848387;
	private int vida = 0xFFB6FF00;
	private int municao = 0xFF0026FF;
	private int inimigo = 0xFF8C0000;
	private int player = 0xFFFFFFFF;
	private int arma = 0xFFFF00DC;
	public static int WIDTH,HEIGHT;
	
	public int qtd = 0;
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
			System.out.println(WIDTH+" "+HEIGHT);
			for(int xx= 0;xx<map.getWidth();xx++) {
				for(int yy = 0;yy<map.getHeight();yy++) {
					
					int px = pixels[xx+(yy*map.getWidth())];
					if(px == grama) {
						tiles[xx+(yy*map.getWidth())] = new FloorTile(Tile.TILE_GRAMA, xx*16, yy*16);
					}else if(px == parede) {
						tiles[xx+(yy*map.getWidth())] = new WallTile(Tile.TILE_PAREDE, xx*16, yy*16);
						qtd+=1;
					}
					else {
						tiles[xx+(yy*map.getWidth())] = new FloorTile(Tile.TILE_GRAMA, xx*16, yy*16);
					}
					if(px == player) {
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
						
					}else if(px == inimigo) {
						Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY));
					}else if(px == vida) {
						Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK));
					}else if(px == municao) {
						Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET));
					}else if(px == arma) {
						System.out.println("arma");
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON));
					}
					
				}
			}
			System.out.println(qtd+"qtd parede");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void render(Graphics g) {
		int xstart  = Camera.x>>4;
		int ystart = Camera.y>>4;
		
		int xfinal = xstart+(Game.WIDTH>>4);
		int yfinal = ystart+(Game.HEIGHT>>4);
		
		for(int xx = xstart;xx<=xfinal;xx++) {
			for(int yy =ystart;yy<=yfinal;yy++) {
				if(xx<0 || yy<0 ||xx>= WIDTH ||yy >=HEIGHT ) {
					continue;
				}
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			}
		}
	}
}
