package main;

import javax.swing.JFrame;

public class GameWindow{
	
	private JFrame jframe;
	
	
	public GameWindow(GamePanel gamePanel) {
		
		jframe = new JFrame();
		
		
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.pack(); //Fit size of window to preferred size of components (jpanel - 1280x800)
		jframe.setVisible(true); // Make sure this is always last
		
		
	}
}
