package de.febrildur.sieveeditor;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.fluffypeople.managesieve.ParseException;
import com.fluffypeople.managesieve.SieveScript;

import de.febrildur.sieveeditor.actions.ActionConnect;
import de.febrildur.sieveeditor.actions.ActionLoadScript;

public class Application extends JFrame {

	private ConnectAndListScripts server;
	private PropertiesSieve prop = new PropertiesSieve();
	private RSyntaxTextArea textArea;

	public Application() {

		try {
			prop.load();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
			return;
		}
		
		JMenuBar menu = new JMenuBar();
		JMenu sieve = new JMenu("Sieve");
		menu.add(sieve);
		
		sieve.add(new JMenuItem(new ActionConnect(this)));
		sieve.add(new JMenuItem(new ActionLoadScript(this)));
		
		setJMenuBar(menu);
		
		
		JPanel cp = new JPanel(new BorderLayout());

		textArea = new RSyntaxTextArea(20, 60);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		RTextScrollPane sp = new RTextScrollPane(textArea);
		cp.add(sp);

		setContentPane(cp);
		setTitle("Text Editor Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);

	}

	public PropertiesSieve getProp() {
		return prop;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Application().setVisible(true);
			}
		});
	}

	public ConnectAndListScripts getServer() {
		return server;
	}

	public void setServer(ConnectAndListScripts server) {
		this.server = server;
	}
	
	public void setScript(SieveScript script) throws IOException, ParseException {
		textArea.setText(server.getScript(script));
	}
}
