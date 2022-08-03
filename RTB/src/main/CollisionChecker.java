package main;

import entity.Entity;
import object.SuperObject;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkObject(Entity entity, int index) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y;
		int entityTopWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		if (gp.objects[index].worldX < entityRightWorldX 
				&& gp.objects[index].worldY < entityTopWorldY 
				&& gp.objects[index].worldX+gp.tileSize > entityLeftWorldX 
				&& gp.objects[index].worldY+gp.tileSize > entityBottomWorldY) {
			gp.inventory[index] = gp.objects[index];
			gp.objects[index] = null;
			
		}
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
			
			if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
			
			if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
			
			
		}
	}
}
