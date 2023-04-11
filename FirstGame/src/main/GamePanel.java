package main;

import java.awt.Graphics;
//import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Dimension;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{
	
	//Mult. mouse listeners, so one MouseInput declared here
	private MouseInputs mouseInputs;
	
	//Control rectangle position
	private float xDelta = 100, yDelta = 100;
	
	//Fps counter
	private int frames = 0;
	private long lastCheck = 0;
	
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
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
		
		
	}
	

}
