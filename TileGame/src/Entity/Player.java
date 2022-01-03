package Entity;

import Main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GameWindow;

public class Player extends Entity{
	
	GameWindow gameWindow;
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GameWindow gameWindow, KeyHandler keyHandler) {
		this.gameWindow = gameWindow;
		this.keyHandler = keyHandler;
		this.screenX = gameWindow.SCREENWIDTH/2 - (gameWindow.TILESIZE);
		this.screenY = gameWindow.SCREENHEIGHT/2 - (gameWindow.TILESIZE);
		
		hitBox = new Rectangle(-16,32,28,16);
		
		setDefaultValues();
		getSprite();
	}
	
	public void setDefaultValues() {
		worldX = (int)(gameWindow.TILESIZE * 25);
		worldY = (int)(gameWindow.TILESIZE * 25);
		speed = 4;
		direction = "down";
		
	}
	
	public void getSprite() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			
			sup = ImageIO.read(getClass().getResourceAsStream("/player/sup.png"));
			sdown = ImageIO.read(getClass().getResourceAsStream("/player/sdown.png"));
			sleft = ImageIO.read(getClass().getResourceAsStream("/player/sleft.png"));
			sright = ImageIO.read(getClass().getResourceAsStream("/player/sright.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		if(keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {
		
			if(keyHandler.up) {
				direction = "up";
			}
			else if(keyHandler.down) {
				direction = "down";
			}
			else if(keyHandler.left) {
				direction = "left";
			}
			else if(keyHandler.right) {
				direction = "right";
			}
		}
		else {
			switch(direction) {
			case "up":
				direction = "sup";
				break;
			case "down":
				direction = "sdown";
				break;
			case "left":
				direction = "sleft";
				break;
			case "right":
				direction = "sright";
				break;
			}
			
		}
		
		colliding=false;
		gameWindow.colManager.checkTile(this);
		
		if(colliding ==false) {
			switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
			}
			
		}
		
		frameCounter++;
		if(frameCounter > 12) {
			if(frame == 1) {
				frame=2;
			}
			else if(frame ==2) {
				frame=1;
			}
			frameCounter=0;
		}
	}
	
	public void draw(Graphics2D g2D) {
		BufferedImage sprite = null;
		
		switch(direction) {
		case "up":
			if(frame==1) {
				sprite = up1;
			}
			if(frame==2) {
				sprite = up2;
			}
			break;
		case "down":
			if(frame==1) {
				sprite = down1;
			}
			if(frame==2) {
				sprite = down2;
			}
			break;
		case "left":
			if(frame==1) {
				sprite = left1;
			}
			if(frame==2) {
				sprite = left2;
			}
			break;
		case "right":
			if(frame==1) {
				sprite = right1;
			}
			if(frame==2) {
				sprite = right2;
			}
			break;
		case "sup":
			sprite = sup;
			break;
		case "sdown":
			sprite = sdown;
			break;
		case "sleft":
			sprite = sleft;
			break;
		case "sright":
			sprite = sright;
			break;
		}
		g2D.drawImage(sprite, screenX, screenY, gameWindow.TILESIZE*2, gameWindow.TILESIZE*2, null);
	}
}
