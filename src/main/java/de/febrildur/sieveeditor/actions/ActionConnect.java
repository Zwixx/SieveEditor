package de.febrildur.sieveeditor.actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fluffypeople.managesieve.ParseException;

import de.febrildur.sieveeditor.Application;
import de.febrildur.sieveeditor.system.ConnectAndListScripts;

public class ActionConnect extends AbstractAction {

	private Application parentFrame;

	public ActionConnect(Application parentFrame) {
		putValue("Name", "Connect...");
		this.parentFrame = parentFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		GridLayout layout = new GridLayout(5, 2, 6, 6);
		panel.setLayout(layout);

		final JDialog frame = new JDialog(parentFrame, "Connection", true);
		frame.getContentPane().add(panel);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(parentFrame);

		JLabel labelServer = new JLabel("Server");
		panel.add(labelServer);
		JTextField tfServer = new JTextField(parentFrame.getProp().getServer(), 15);
		panel.add(tfServer);
		
		JLabel labelPort = new JLabel("Port");
		panel.add(labelPort);
		JTextField tfPort = new JTextField(Integer.toString(parentFrame.getProp().getPort()), 15);
		panel.add(tfPort);
		
		JLabel labelUsername = new JLabel("User");
		panel.add(labelUsername);
		JTextField tfUsername = new JTextField(parentFrame.getProp().getUsername(), 15);
		panel.add(tfUsername);
		
		JLabel labelPassword = new JLabel("Password");
		panel.add(labelPassword);
		JTextField tfPassword = new JTextField(parentFrame.getProp().getPassword(), 15);
		panel.add(tfPassword);
		
		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener((event) -> {
			parentFrame.setServer(new ConnectAndListScripts());
			try {
				parentFrame.getProp().setServer(tfServer.getText());
				parentFrame.getProp().setPort(Integer.valueOf(tfPort.getText()));
				parentFrame.getProp().setUsername(tfUsername.getText());
				parentFrame.getProp().setPassword(tfPassword.getText());
				parentFrame.getServer().connect(parentFrame.getProp());
				parentFrame.getProp().write();
				frame.setVisible(false);
			} catch (NumberFormatException | IOException | ParseException e1) {
				JOptionPane.showMessageDialog(frame, e1.getClass().getName() + ": " + e1.getMessage());
			}
		});
		panel.add(buttonOK);
		
		
		frame.setVisible(true);
	}

}
