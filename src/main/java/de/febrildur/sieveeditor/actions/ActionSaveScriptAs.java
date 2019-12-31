package de.febrildur.sieveeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.febrildur.sieveeditor.Application;
import de.febrildur.sieveeditor.ConnectAndListScripts;

public class ActionSaveScriptAs extends AbstractAction {

	private Application parentFrame;

	public ActionSaveScriptAs(Application parentFrame) {
		putValue("Name", "Save as...");
		this.parentFrame = parentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();

		final JDialog frame = new JDialog(parentFrame, "Connection", true);
		frame.getContentPane().add(panel);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(parentFrame);

		JLabel labelScriptName = new JLabel("Scriptname");
		panel.add(labelScriptName);
		JTextField tfScriptName = new JTextField(parentFrame.getProp().getServer(), 15);
		panel.add(tfScriptName);
		
		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener((event) -> {
			parentFrame.setServer(new ConnectAndListScripts());
			try {
				parentFrame.save(tfScriptName.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, e1.getClass().getName() + ": " + e1.getMessage());
			}
		});
		panel.add(buttonOK);
		
		frame.setVisible(true);
	}
}
