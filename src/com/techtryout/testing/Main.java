package com.techtryout.testing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Sign In");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final Login newContentPane = new Login(frame);
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

}
