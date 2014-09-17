package de.szut.uelker;

import java.io.IOException;
import java.sql.*;

public class Database {
	private String path;
	private String username;
	private String password;
	private Connection con;

	public Database(String path, String username, String password) throws IOException {
		this.path = path;
		this.username = username;
		this.password = password;

		initDB();
	}

	protected void initDB() throws IOException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");

			con = DriverManager.getConnection("jdbc:hsqldb:file:" + getPath() + ";shutdown=true", getUsername(), getPassword());

		} catch (ClassNotFoundException e) {
			throw new IOException(e);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public void ensureConnection() throws IOException {
		if(con == null) initDB();
	}



	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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