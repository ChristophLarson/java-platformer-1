package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;

	public static final int OUTSIDE_GRID_WIDTH = 12;
	public static final int OUTSIDE_GRID_HEIGHT = 4;

	public LevelManager(Game game) {
		this.game = game;
		// levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSprites();
		levelOne = new Level(LoadSave.getLevelData());

	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[OUTSIDE_GRID_WIDTH
				* OUTSIDE_GRID_HEIGHT];
		for (int j = 0; j < OUTSIDE_GRID_HEIGHT; j++) {
			for (int i = 0; i < OUTSIDE_GRID_WIDTH; i++) {
				int index = j * OUTSIDE_GRID_WIDTH + i;
				levelSprite[index] = img.getSubimage(
						i * Game.TILES_DEFAULT_SIZE,
						j * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE,
						Game.TILES_DEFAULT_SIZE);
			}
		}

	}

	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i,
						Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE,
						null);

			}
		}

	}

	public void update() {

	}

	public Level getCurrentLevel() {
		return levelOne;
	}
}
