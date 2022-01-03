package Main;

import Entity.Entity;

public class CollisionHandler {
	
	GameWindow gameWindow;
	
	public CollisionHandler(GameWindow gameWindow) {
		this.gameWindow=gameWindow;
	}
	
	public void checkTile(Entity entity) {
		int leftX = entity.worldX + entity.hitBox.x;
		int rightX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
		int topY = entity.worldY + entity.hitBox.y;
		int botY = entity.worldY + entity.hitBox.y + entity.hitBox.height;
		
		int leftCol = leftX/gameWindow.TILESIZE;
		int rightCol = rightX/gameWindow.TILESIZE;
		
		int topRow = topY/gameWindow.TILESIZE;
		int botRow = botY/gameWindow.TILESIZE;
		
		int tile1, tile2;
		
		switch(entity.direction) {
		case "up":
			topRow = (topY - entity.speed)/gameWindow.TILESIZE;
			tile1 = gameWindow.tileManager.map[leftCol][topRow];
			tile2 = gameWindow.tileManager.map[rightCol][topRow];
			if(gameWindow.tileManager.tile[tile1].collider == true || gameWindow.tileManager.tile[tile2].collider == true) {
				entity.colliding = true;
			}
			break;
		case "down":
			botRow = (botY + entity.speed)/gameWindow.TILESIZE;
			tile1 = gameWindow.tileManager.map[leftCol][botRow];
			tile2 = gameWindow.tileManager.map[rightCol][botRow];
			if(gameWindow.tileManager.tile[tile1].collider == true || gameWindow.tileManager.tile[tile2].collider == true) {
				entity.colliding = true;
			}
			break;
		case "left":
			leftCol = (leftX - entity.speed)/gameWindow.TILESIZE;
			tile1 = gameWindow.tileManager.map[leftCol][topRow];
			tile2 = gameWindow.tileManager.map[rightCol][botRow];
			if(gameWindow.tileManager.tile[tile1].collider == true || gameWindow.tileManager.tile[tile2].collider == true) {
				entity.colliding = true;
			}
			break;
		case "right":
			rightCol = (rightX + entity.speed)/gameWindow.TILESIZE;
			tile1 = gameWindow.tileManager.map[leftCol][topRow];
			tile2 = gameWindow.tileManager.map[rightCol][botRow];
			if(gameWindow.tileManager.tile[tile1].collider == true || gameWindow.tileManager.tile[tile2].collider == true) {
				entity.colliding = true;
			}
			break;
		}
	}
}
