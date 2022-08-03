package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
  final int originalTileSize = 16;
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;

  int FPS = 60;

  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = maxScreenCol * tileSize;
  public final int screenHeight = maxScreenRow * tileSize;
  
  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldCol;
  public final int worldHeight = tileSize * maxWorldRow;

  KeyHandler kh = new KeyHandler();
  Thread gameThread;
  public CollisionChecker cChecker = new CollisionChecker(this);
  public Player player = new Player(this, kh);
  TileManager tm = new TileManager(this);
  public SuperObject[] objects = new SuperObject[1];
  public SuperObject[] inventory = new SuperObject[50];
  AssetSetter as = new AssetSetter(this);

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);     
    this.addKeyListener(kh);
    this.setFocusable(true);
    
    as.setObjects();
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    long drawCount = 0;
    
    while (gameThread != null) {
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
        drawCount++;
      }

      if (timer >= 1000000000) {
        drawCount = 0;
        timer = 0;
      }
      
    }
  }
  
  public void update() {
    player.update();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;
    tm.draw(g2);
    player.draw(g2);
    for (int i = 0; i < objects.length; i++) {
    	if (objects[i] == null) {
    		continue;
    	}
    	objects[i].draw(g2, this);
    	cChecker.checkObject(player, i);
    }
    
    

    g2.dispose();
  }
}
