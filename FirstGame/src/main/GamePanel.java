package main;

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;
	
	//Control rectangle position
	private int xDelta = 0, yDelta = 0;
	
	public GamePanel() {
		
		mouseInputs = new MouseInputs();
		
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(100 + xDelta, 100 + yDelta, 200, 50);
	}

}
