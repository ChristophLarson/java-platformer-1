package entities;

import static utils.Constants.Dimensions.SPRITE_GRID_HEIGHT;
import static utils.Constants.Dimensions.SPRITE_GRID_WIDTH;
import static utils.Constants.Dimensions.SPRITE_HEIGHT;
import static utils.Constants.Dimensions.SPRITE_WIDTH;
//import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.getSpriteAmount;
import static utils.HelpMethods.canMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class Player extends Entity {

	private BufferedImage[][] animations;

	private int aniTick, aniIndex, aniSpeed = 120 / 8;
	private int playerAction = IDLE;
	private boolean moving, attacking = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();

	}

	public void update() {
		updatePos();
		updateHitbox();
		updateAnimationTick();
		setAnimation();

	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width,
				height, null);
		drawHitbox(g);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}

		if (attacking) {
			playerAction = ATTACK_1;
		}

		if (startAni != playerAction) {
			resetAniTick();
		}

	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;

	}

	private void updatePos() {

		moving = false;
		if (!left && !right && !up && !down)
			return;
		float xSpeed = 0, ySpeed = 0;

		if (left && !right) {
			xSpeed = -playerSpeed;
		} else if (right && !left) {
			xSpeed = playerSpeed;
		}

		if (up && !down) {
			ySpeed = -playerSpeed;
		} else if (down && !up) {
			ySpeed = playerSpeed;
		}

		if (canMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
			this.x += xSpeed;
			this.y += ySpeed;
			moving = true;
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

		// Size of sprite grid
		animations = new BufferedImage[SPRITE_GRID_HEIGHT][SPRITE_GRID_WIDTH];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i * SPRITE_WIDTH,
						j * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
			}
		}
	}

	public void loadLevelData(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

}
