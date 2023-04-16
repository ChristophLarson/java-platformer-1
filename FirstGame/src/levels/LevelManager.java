package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;

	private final int OUTSIDE_GRID_WIDTH = 12;
	private final int OUTSIDE_GRID_HEIGHT = 4;

	public LevelManager(Game game) {
		this.game = game;
		// levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSprites();
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[OUTSIDE_GRID_WIDTH
				* OUTSIDE_GRID_HEIGHT];
		for (int j = 0; j < OUTSIDE_GRID_HEIGHT; j++) {
			for (int i = 0; i < OUTSIDE_GRID_WIDTH; i++) {
				int index = j * OUTSIDE_GRID_WIDTH + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
		}

	}

	public void draw(Graphics g) {
		g.drawImage(levelSprite[2], 0, 0, null);
	}

	public void update() {

	}
}
