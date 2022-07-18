package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity {
	GamePanel gp;
	KeyHandler kh;
	
	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler kh) {
		this.gp = gp;
		this.kh = kh;
		
		screenX = gp.screenWidth/2-(gp.tileSize/2);
		screenY = gp.screenHeight/2-(gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 10;
		speed = 4;
		direction = "down";
	}

	public void update() {
		if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
			if (kh.upPressed) {
				direction = "up";
				worldY -= speed;
			} else if (kh.downPressed) {
				direction = "down";
				worldY += speed;
			} else if (kh.leftPressed) {
				direction = "left";
				worldX -= speed;
			} else if (kh.rightPressed) {
				direction = "right";
				worldX += speed;
			}
			spriteCounter++;
			if (spriteCounter > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
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
