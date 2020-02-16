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
import de.febrildur.sieveeditor.system.ConnectAndListScripts;

public class ActionSaveScriptAs extends AbstractAction {

	private Application parentFrame;

	public ActionSaveScriptAs(Application parentFrame) {
		putValue("Name", "Save as...");
		this.parentFrame = parentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String newName = JOptionPane.showInputDialog("Rename to:", parentFrame.getScriptName());

		try {
			parentFrame.save(newName);
			parentFrame.updateStatus();
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(parentFrame, e1.getClass().getName() + ": " + e1.getMessage());
		}
	}
}
