package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Uzi extends SuperObject {
	public OBJ_Uzi() {
		
		name = "uzi";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/gun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
}
