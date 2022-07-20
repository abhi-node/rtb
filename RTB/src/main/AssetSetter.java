package main;

import object.OBJ_Uzi;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObjects() {
		gp.objects[0] = new OBJ_Uzi();
		gp.objects[0].worldX = 432;
		gp.objects[0].worldY = 480;
	}
}
