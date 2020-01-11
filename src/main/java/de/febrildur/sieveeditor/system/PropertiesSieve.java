package de.febrildur.sieveeditor.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesSieve {

	private String server;
	private int port;
	private String username;
	private String password;
	
	private final String propFileName = System.getProperty("user.home") + File.separator + ".sieveproperties";
	
	public void load() throws IOException {
		File propFile = new File(propFileName);
		propFile.createNewFile();
		try (InputStream input = new FileInputStream(propFileName)) {
			Properties prop = new Properties();

			prop.load(input);

			server = prop.getProperty("sieve.server", "");
			port = Integer.valueOf(prop.getProperty("sieve.port", "4190"));
			username = prop.getProperty("sieve.user", "");
			password = prop.getProperty("sieve.password", "");
		}
	}

	public void write() {
		try (OutputStream output = new FileOutputStream(propFileName)) {

			Properties prop = new Properties();

			// set the properties value
			prop.setProperty("sieve.server", server);
			prop.setProperty("sieve.port", Integer.toString(port));
			prop.setProperty("sieve.user", username);
			prop.setProperty("sieve.password", password);

			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
