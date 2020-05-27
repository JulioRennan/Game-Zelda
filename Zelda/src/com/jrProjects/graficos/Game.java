package com.jrProjects.graficos;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.jrProjects.entities.Entity;
import com.jrProjects.entities.Player;

import com.jrProjects.word.World;

public class Game extends Canvas implements Runnable,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean isRunning ;
	
	
	public static JFrame frame;
	public static int WIDTH = 240;
	public static int HEIGHT = 160;
	private final int SCALE = 3;
	
	public static List<Entity> entities;
	public static SpriteSheet spriteSheet;
	private BufferedImage image;
	
	
	
	private World map;
	
	public static Player player;
	
	public  Game() {
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	
		image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		initFrame();
		
		
		entities = new ArrayList<Entity>();
		spriteSheet = new SpriteSheet("/sprite.png");
		
		player = new Player(0, 0, 16, 16, spriteSheet.getSprite(32, 0, 16, 16));
		
		entities.add(player); 
		
		map = new World("/mapa.png");
		
	
		
	}
	
	public void initFrame() {
		frame = new JFrame("Zelda");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
	}
	public synchronized void stop() {
		isRunning = false;
		try {
			
			thread.join();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String args[]) {
		Game ex = new Game();
		ex.start();
	}
	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(19,19,19));
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g.dispose();
		g = image.getGraphics();
		map.render(g);

		for(Entity e:entities) {
			e.render(g);
		}
	
		bs.show();
		
	}
	public void tick() {
		for(Entity e:entities) {
			e.tick();
		}
	}
	
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double  ns = 1000000000/amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta+=(now - lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				tick();
				render();
				frames++;
				delta --;
			}
			if(System.currentTimeMillis() - timer>=1000) {
				//System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_A) {
			player.left = true;
			
		}else if(e.getKeyCode()==KeyEvent.VK_D) {
			player.right = true;
		}if(e.getKeyCode()==KeyEvent.VK_W) {
			player.up = true;
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A) {
			player.left = false;
		}else if(e.getKeyCode()==KeyEvent.VK_D) {
			player.right = false;
		}if(e.getKeyCode()==KeyEvent.VK_W) {
			player.up = false;
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
