package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import Entity.Player;
import Tile.TileManager;

@SuppressWarnings("serial")
public class GameWindow extends JPanel implements Runnable{
	
	//Screen Settings
	final int BASETILESIZE = 16;
	final int SCALER = 3;
    public final int TILESIZE = BASETILESIZE * SCALER;
    public final int SCREENX = 20;
    public final int SCREENY = 15;
    public final int SCREENWIDTH = TILESIZE * SCREENX;
    public final int SCREENHEIGHT = TILESIZE * SCREENY;
    final int FPS = 60;
    
    //World Settings
    public final int maxCol=50;
    public final int maxRow=50;
    public final int worldWidth = TILESIZE * maxCol;
    public final int worldHeight = TILESIZE * maxRow;
    
    //TileManager
    TileManager tileManager = new TileManager(this);
    
    //KeyHandler
    KeyHandler keyHandler = new KeyHandler();
    
    //Thread
    Thread gameThread;
    
    //Entities
    public Player player = new Player(this,keyHandler);
    
    //CollisionManager
    public CollisionHandler colManager = new CollisionHandler(this);
    
    public GameWindow(){
    	
    	this.setPreferredSize(new Dimension (SCREENWIDTH, SCREENHEIGHT));
    	this.setBackground(new Color(73,147,0));
    	this.setDoubleBuffered(true);
    	this.addKeyListener(keyHandler);
    	this.setFocusable(true);
    	
    }

    public void start() {
    	
    	gameThread = new Thread(this);
    	gameThread.start();
    	
    }
    
    
    //Game Loop
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta =0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer =0;
		int FPSCounter =0;
			
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >=1) {
				update();
			
				repaint();
				
				delta--;
				FPSCounter++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: "+ FPSCounter);
				FPSCounter = 0;
				timer =0;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g;
		
		tileManager.draw(g2D);
		
		player.draw(g2D);
		
		g2D.dispose();
		
	}
}
