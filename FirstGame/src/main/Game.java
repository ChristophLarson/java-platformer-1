package main;

import static utils.Constants.Dimensions.SPRITE_HEIGHT;
import static utils.Constants.Dimensions.SPRITE_WIDTH;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameLoopThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Player player;
	private LevelManager levelManager;

	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); // 48
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocusInWindow();

		startGameLoop();
	}

	private void initClasses() {
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int) (SPRITE_WIDTH * SCALE),
				(int) (SPRITE_HEIGHT * SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());

	}

	private void startGameLoop() {
		gameLoopThread = new Thread(this);
		gameLoopThread.start();
	}

	public void update() {
		player.update();
		levelManager.update();
	}

	public void render(Graphics g) {
		levelManager.draw(g);
		player.render(g);
	}

	@Override
	public void run() {

		// Using nanoseconds
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET; // Frequency of updates

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			// deltaU will be >= 1.0 when the duration since the last update
			// is equal or greater than timePerUpdate, keeping track of exactly
			// HOW much time since the last update has elapsed, not just whether
			// enough time has elapsed to warrant an update;
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--; // Keep amount over 1.0 to store "wasted" time between
							// updates
			}

			// Has the current frame been displayed for its full duration
			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			// Output current FPS
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out
						.println("FPS: " + frames + " | " + "UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

}
