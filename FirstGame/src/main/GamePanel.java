package main;

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{
	
	//Mult. mouse listeners, so one MouseInput declared here
	private MouseInputs mouseInputs;
	
	//Control rectangle position
	private int xDelta = 100, yDelta = 100;
	
	public GamePanel() {
		
		mouseInputs = new MouseInputs(this);
		
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void ChangeXDelta(int delta) {
		
		this.xDelta += delta;
		repaint();
	}
	
	public void ChangeYDelta(int delta) {
		
		this.yDelta += delta;
		repaint();
	}
	
	//Redraw rect at click location
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(xDelta, yDelta, 200, 50);
	}

}
