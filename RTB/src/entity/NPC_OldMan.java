package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class NPC_OldMan extends Entity {
	GamePanel gp;
	
	public int screenX;
	public int screenY;
	public int turnValue;

	public NPC_OldMan(GamePanel gp) {
		this.gp = gp;
		
		screenX = worldX - gp.player.worldX + gp.player.screenX;
		screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		turnValue = 0;
		setDefaultValues();
		getNPCImage();
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		
	}
	
	public void getNPCImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/oldman/oldman_right_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 12;
		speed = 1;
		direction = "down";
	}
	
	public void update() {

		collisionOn = false;
		gp.cChecker.checkTile(this);

		if (!collisionOn) {
			switch (direction) {
			case ("up"):
				worldY -= speed;
				break;
			case ("down"):
				worldY += speed;
				break;
			case ("left"):
				worldX -= speed;
				break;
			case ("right"):
				worldX += speed;
				break;
			}
		}
		spriteCounter++;
		if (spriteCounter > 20) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			turnValue += 1;
			spriteCounter = 0;
		}
		if (turnValue > 10) {
			if (direction == "down") {
				direction = "up";
			} else if (direction == "up") {
				direction = "down";
			}
			turnValue = 0;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		screenX = worldX - gp.player.worldX + gp.player.screenX;
		screenY = worldY - gp.player.worldY + gp.player.screenY;
		
	    BufferedImage image = null;
	    switch(direction) {
	    case "up":
	    	if (spriteNum == 1) {
	    		image = up1;
	    	} else if (spriteNum == 2) {
	    		image = up2;
	    	}
	    	break;
	    case "down":
	    	if (spriteNum == 1) {
	    		image = down1;
	    	}
	    	else if (spriteNum == 2) {
	    		image = down2;
	    	}
	    	break;
	    case "left":
	    	if (spriteNum == 1) {
	    		image = left1;
	    	}
	    	else if (spriteNum == 2) {
	    		image = left2;
	    	}
	    	break;
	    case "right":
	    	if (spriteNum == 1) {
	    		image = right1;
	    	}
	    	else if (spriteNum == 2) {
	    		image = right2;
	    	}
	    	break;
	    	
	    }
	    
	    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	  }
}
