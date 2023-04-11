package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
//import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Dimension;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel{
	
	//Mult. mouse listeners, so one MouseInput declared here
	private MouseInputs mouseInputs;
	
	//Control rectangle position
	private float xDelta = 100, yDelta = 100;
	
	private final int SPRITE_WIDTH = 64;
	private final int SPRITE_HEIGHT = 40;
	private final int SPRITE_GRID_WIDTH = 6;
	private final int SPRITE_GRID_HEIGHT = 9;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 120 / 8; // fps / animations per second
	private int playerAction = IDLE;
	private int playerDirection = -1; //Not moving
	private boolean moving = false;
	
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		
		importImg();
		loadAnimations();
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void loadAnimations() {
		animations = new BufferedImage[SPRITE_GRID_HEIGHT][SPRITE_GRID_WIDTH]; // Size of sprite grid
		for(int j = 0; j < animations.length; j++) 
		for(int i = 0; i < animations[j].length; i++) {
			animations[j][i] = img.getSubimage(i*SPRITE_WIDTH, j*SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
		}
		
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		
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
		
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
	}
	
	public void setDirection(int direction) {
		this.playerDirection = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= getSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
		
	}
	
	private void setAnimation() {
		
		if(moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}
		
	}
	

	private void updatePos() {
		if(moving) {
			switch(playerDirection) {
			case LEFT:
				xDelta -=5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateAnimationTick();
		
		setAnimation();
		updatePos();
		
		g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, SPRITE_WIDTH*4, SPRITE_HEIGHT*4, null);
	}

	

}
