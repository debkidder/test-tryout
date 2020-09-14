package com.techtryout.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Login extends JPanel implements ActionListener {
	private static String OK = "ok";
	private JFrame controllingFrame;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JTextField hiddenField;

	public Login(JFrame f) {
		loginField = new JTextField(10);
		loginField.setActionCommand(OK);
		loginField.addActionListener(this);

		passwordField = new JPasswordField(10);
		passwordField.setActionCommand(OK);
		passwordField.addActionListener(this);

		// TODO Will do something with this later
		hiddenField = new JTextField(0);

		JLabel label1 = new JLabel("Login: ");
		label1.setLabelFor(loginField);
		JLabel label2 = new JLabel("Password ");
		label2.setLabelFor(passwordField);

		JComponent buttonPane = createButtonPanel();

		JPanel labelPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel fieldPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		labelPane.add(label1);
		fieldPane.add(loginField);
		labelPane.add(label2);
		fieldPane.add(passwordField);

		labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.Y_AXIS));
		fieldPane.setLayout(new BoxLayout(fieldPane, BoxLayout.Y_AXIS));

		add(labelPane);
		add(fieldPane);
		add(buttonPane);

	}

	// (Intentional BUG) Low priority; should be 'OK' for consistency, etc.
	protected JComponent createButtonPanel() {
		JPanel p = new JPanel();
		JButton okButton = new JButton("Ok");

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		okButton.setActionCommand(OK);
		okButton.addActionListener(this);

		p.add(okButton);

		return p;
	}

	//  (Intentional BUG) Low-priority "Error_" that shows in dialog title
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();

		if (OK.equals(cmd)) {
			char[] input = passwordField.getPassword();
			String input2 = loginField.getText();

			if (isLoginCorrect(input2)) {
				if (isPasswordCorrect(input)) {
					JOptionPane.showMessageDialog(controllingFrame, "Successful!");
				}
			} else if (isLoginEmpty(input2)) { 
				JOptionPane.showMessageDialog(controllingFrame, "Please enter a username and password.", // change to
																											// use name
						"Error_", 
						JOptionPane.ERROR_MESSAGE);
			}

			else {
				JOptionPane.showMessageDialog(controllingFrame, "Incorrect. Please try again.", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	private static boolean isLoginCorrect(String input2) {
		boolean isLoginCorrect = true;
		String correctLogin = "Ted";

		// (OK) Accepts Ted (OK)
		// (OK) Accepts ted (OK)
		// (OK) Accepts TED (OK)
		// (Intentional BUG) Accepts empty // I just added this bug, so I'll eventually change code below
		// (Intentional BUG) Accepts anything that starts with Ted, like Teddy
		// (Intentional BUG) Not handling this a standard way, so rejects other case variations (tED, teD, tEd, etc.)
		// (Intentional BUG) Doesn't prevent SQL injections, XSS, etc. (not that I know how yet, lol)
		if ((input2.equals("Ted")) || (input2.equals("ted")) || (input2.equals("TED")) || (input2.equals("")) || (input2.startsWith("Ted"))) {
			isLoginCorrect = true;
		} else {
			isLoginCorrect = false;
		}

		return isLoginCorrect;

	}

	// need to doublecheck this code
	private static boolean isLoginEmpty(String input2) {
		boolean isLoginEmpty = false;

		if (input2.equals("")) {
			isLoginEmpty = true;
		} else {
			isLoginEmpty = false;
		}

		return isLoginEmpty;

	}

	// weird way to handle the password, I know; works for for now though
	private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { 'm', 'o', 'n', 'k', 'e', 'y' }; 

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		return isCorrect;
	}

}

//  OTHER VALID BUGS (if anyone happens to mention them):
//  - (Usability) How the dialog box pulls up in odd places on the screen
//  - (Usability) How it doesn't really log you in
//  - (Usability) Doesn't have standards like "Forgot Password" etc.