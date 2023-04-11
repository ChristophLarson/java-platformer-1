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

public class GamePanel extends JPanel{
	
	//Mult. mouse listeners, so one MouseInput declared here
	private MouseInputs mouseInputs;
	
	//Control rectangle position
	private float xDelta = 100, yDelta = 100;
	
	private BufferedImage img, subImg;
	
	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		
		importImg();
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		subImg = img.getSubimage(1*64, 8*40, 64, 40);
		g.drawImage(subImg, (int)xDelta, (int)yDelta, 128, 80, null);
	}
	

}
