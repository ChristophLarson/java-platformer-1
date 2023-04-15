package utils;

import static utils.Constants.Dimensions.SPRITE_GRID_HEIGHT;
import static utils.Constants.Dimensions.SPRITE_GRID_WIDTH;
import static utils.Constants.Dimensions.SPRITE_HEIGHT;
import static utils.Constants.Dimensions.SPRITE_WIDTH;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	
	public static BufferedImage GetPlayerAtlas() {

		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/player_sprites.png");
					
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return img;
	}
	
}
