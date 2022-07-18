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
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		loadMap("/maps/map01.txt");
		
		for (int i = 0; i < mapTileNum.length; i++) {
			for (int j = 0; j < mapTileNum[i].length; j++) {
				g2.drawImage(tile[mapTileNum[i][j]].image, i*gp.tileSize, j*gp.tileSize, gp.tileSize, gp.tileSize, null);
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
