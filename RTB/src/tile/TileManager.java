package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		loadMap("/maps/map01.txt");
		
		for (int i = 0; i < mapTileNum.length; i++) {
			for (int j = 0; j < mapTileNum[i].length; j++) {
				
				int worldX = i*gp.tileSize;
				int worldY = j*gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
				if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
						worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
						worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
						worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
					g2.drawImage(tile[mapTileNum[i][j]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
				
			}
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			for (int i = 0; i < gp.maxWorldCol; i++) {
				String line = br.readLine();
				
				
				String[] numbers = line.split(" ");
				for (int j = 0; j < gp.maxWorldRow; j++) { 
					int num = Integer.parseInt(numbers[j]);
					mapTileNum[i][j] = num;
				}
				
			}
			
			
			
			
			
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
