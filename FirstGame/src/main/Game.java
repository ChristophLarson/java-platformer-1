package main;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameLoopThread;
	private final int FPS_SET = 120;
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
	
	@Override
	public void run() {
		
		//Using nanoseconds
		double timePerFrame =  1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		while(true) {
			
			now = System.nanoTime();
			//Has the current frame been displayed for its full duration
			if(now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			//Output current FPS
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		
	}

}
