package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, sup, sdown, sleft, sright;
	public String direction;
	
	public int frameCounter =0;
	public int frame=1;
	
	public Rectangle hitBox;
	public boolean colliding =false;
}
