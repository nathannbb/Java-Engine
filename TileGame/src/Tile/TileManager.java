package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GameWindow;

public class TileManager {
	GameWindow gameWindow;
	public Tile[] tile;
	public int map[][];
	
	public TileManager(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		tile =  new Tile[10];
		
		map = new int[gameWindow.maxCol][gameWindow.maxRow];
		
		getTiles();
		loadMap("/maps/map1.txt");
	}
	
	public void getTiles() {
		
		 try {
			 
				 tile[0] = new Tile(); tile[0].art = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
				 
				 tile[1] = new Tile(); tile[1].art = ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png"));
				 tile[1].collider = true;
				 
				 tile[2] = new Tile(); tile[2].art = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
				 tile[2].collider =true;
				 
				 tile[3] = new Tile(); tile[3].art = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
				 
				 tile[4] = new Tile(); tile[4].art = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));
				 tile[4].collider = true;
				 
				 tile[5] = new Tile(); tile[5].art = ImageIO.read(getClass().getResourceAsStream("/tiles/tree2.png"));
				 tile[5].collider = true;
				 
				 tile[6] = new Tile(); tile[6].art = ImageIO.read(getClass().getResourceAsStream("/tiles/tree3.png"));
				 tile[6].collider = true;
				 
				 tile[7] = new Tile(); tile[7].art = ImageIO.read(getClass().getResourceAsStream("/tiles/tree4.png"));
				 tile[7].collider = true;
			 
		 }catch(IOException e) { 
			 e.printStackTrace(); 
		 }
		 
	}
	
	public void loadMap(String file) {
		
		try {
			InputStream is = getClass().getResourceAsStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gameWindow.maxCol && row < gameWindow.maxRow) {
				
				String line = br.readLine();
				
				while(col < gameWindow.maxCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					map[col][row] = num;
					col++;
					
				}
				
				if(col == gameWindow.maxCol) {
					col =0;
					row++;
				}
			
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2D) {
		
		int worldCol =0;
		int worldRow =0;
		
		while(worldCol < gameWindow.maxCol && worldRow < gameWindow.maxRow) {
			
			int tileMap = map[worldCol][worldRow];
			
			int worldX = worldCol * gameWindow.TILESIZE;
			int worldY = worldRow * gameWindow.TILESIZE;
			int screenX = worldX - gameWindow.player.worldX + gameWindow.TILESIZE + gameWindow.player.screenX;
			int screenY = worldY - gameWindow.player.worldY + gameWindow.TILESIZE + gameWindow.player.screenY;
			
			if(worldX + gameWindow.TILESIZE*2 > gameWindow.player.worldX - gameWindow.player.screenX && worldX - gameWindow.TILESIZE*2 < gameWindow.player.worldX + gameWindow.player.screenX && 
			   worldY + gameWindow.TILESIZE*2 > gameWindow.player.worldY - gameWindow.player.screenY && worldY - gameWindow.TILESIZE*2 < gameWindow.player.worldY + gameWindow.player.screenY) {
				
				g2D.drawImage(tile[tileMap].art, screenX, screenY, gameWindow.TILESIZE, gameWindow.TILESIZE, null);
				
			}
			
			worldCol++;
			
			if(worldCol == gameWindow.maxCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
		
	}
}
