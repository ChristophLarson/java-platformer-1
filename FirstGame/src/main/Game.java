package main;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameLoopThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocusInWindow();
		startGameLoop();
	}
	
	private void startGameLoop() {
		gameLoopThread = new Thread(this);
		gameLoopThread.start();
	}
	
	public void update() {
		gamePanel.updateGame();
	}
	
	@Override
	public void run() {
		
		//Using nanoseconds
		double timePerFrame =  1000000000.0 / FPS_SET;
		double timePerUpdate =  1000000000.0 / UPS_SET; //Frequency of updates
		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			long currentTime = System.nanoTime();
			
			//deltaU will be >= 1.0 when the duration since the last update
			//is equal or greater than timePerUpdate, keeping track of exactly
			//HOW much time since the last update has elapsed, not just whether
			//enough time has elapsed to warrant an update;
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--; //Keep amount over 1.0 to store "wasted" time between updates
			}
			
			//Has the current frame been displayed for its full duration
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			

			//Output current FPS
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
				frames = 0;
				updates = 0;
			}
			
		}
		
	}

}
