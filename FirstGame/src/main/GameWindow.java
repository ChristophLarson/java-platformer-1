package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {

	private JFrame jframe;

	public GameWindow(final GamePanel gamePanel) {

		jframe = new JFrame();

		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.pack(); // Fit size of window to preferred size of components
						// (jpanel - 1280x800)
		jframe.setVisible(true); // Make sure this is always last
		jframe.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();

			}

			@Override
			public void windowGainedFocus(WindowEvent e) {

			}

		});

	}
}
