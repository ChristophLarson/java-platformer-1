package main;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{
	
	//Mult. mouse listeners, so one MouseInput declared here
	private MouseInputs mouseInputs;
	
	//Control rectangle position
	private float xDelta = 100, yDelta = 100;
	
	private float xDir = 0.1f, yDir = 0.1f;
	
	//Fps counter
	private int frames = 0;
	private long lastCheck = 0;
	
	//Color for chameleon rect
	private Color color = new Color(10, 255, 25);
	
	private Random random;
	
	public GamePanel() {
		random = new Random();
		mouseInputs = new MouseInputs(this);
		
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void ChangeXDelta(int delta) {
		
		this.xDelta += delta;
		//repaint();
	}
	
	public void ChangeYDelta(int delta) {
		
		this.yDelta += delta;
		//repaint();
	}
	
	//Redraw rect at click location
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		//repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateRectangle();
		g.setColor(color);
		
		g.fillRect((int)xDelta, (int)yDelta, 200, 50);
		
		//Output current FPS
		frames++;
		if(System.currentTimeMillis() - lastCheck >= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames);
			frames = 0;
		}
		
		repaint();
		//Toolkit.getDefaultToolkit().sync();
		
	}
	
	private void updateRectangle() {
		
		//Move rect on its own
		xDelta+= xDir;
		
		//Reverse dir if hits edge
		if(xDelta >= 400 || xDelta < 0) {
			xDir *=-1;
			color = getRndColor();
		}
		
		
		yDelta += yDir;
		if(yDelta >= 400 || yDelta < 0) {
			yDir *= -1;
			color = getRndColor();
		}
		
	}
	
	private Color getRndColor() {
		int r = random.nextInt(256);
		int b = random.nextInt(256);
		int g = random.nextInt(256);
		
		return new Color(r, g, b);
		
	}

}
