package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
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
		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 8;
		speed = 4;
		direction = "down";
	}

	public void update() {
		if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
			if (kh.upPressed) {
				direction = "up";
				
			} else if (kh.downPressed) {
				direction = "down";
				
			} else if (kh.leftPressed) {
				direction = "left";
				
			} else if (kh.rightPressed) {
				direction = "right";
				
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			if (!collisionOn) {
				switch(direction) {
				case("up"):
					worldY -= speed;
					break;
				case("down"):
					worldY += speed;
					break;
				case("left"):
					worldX -= speed;
					break;
				case("right"):
					worldX += speed;
					break;
				}
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
