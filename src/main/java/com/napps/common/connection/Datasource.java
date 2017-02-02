package com.napps.common.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class Datasource {
	private static org.apache.log4j.Logger log = Logger.getLogger(Datasource.class);

	private Connection connection = null;
	private String driver;
	private String jdbc;
	private String host;
	private String port;
	private String database;
	private String url;
	private String username;
	private String password;
	private String instance;

	public Datasource() {
		try {
			File path = new File("D:/Kalacakra/NAppsManager/src/main/resources/configurationDatabase.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document document = docBuilder.parse(path);

			int i = Integer.parseInt(document.getElementsByTagName("listIdConnection")
					.item(0).getTextContent());

			this.driver = document.getElementsByTagName("driver").item(i)
					.getTextContent();
			this.jdbc = document.getElementsByTagName("jdbc").item(i)
					.getTextContent();
			this.host = document.getElementsByTagName("host").item(i)
					.getTextContent();
			this.port = document.getElementsByTagName("port").item(i)
					.getTextContent();
			this.username = document.getElementsByTagName("username").item(i)
					.getTextContent();
			this.password = document.getElementsByTagName("password").item(i)
					.getTextContent();
			this.database = document.getElementsByTagName("databasename")
					.item(i).getTextContent();
			this.instance = document.getElementsByTagName("instance").item(i)
					.getTextContent();

			this.url = this.jdbc + this.host + this.port + this.database
					+ this.instance;

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Configuration Database not valid " + ex);

		}

	}

	private void connect() throws SQLException {
		try {
			Class.forName(this.driver);
			this.connection = DriverManager.getConnection(this.url,this.username, this.password);
			log.info("Connection Starting.....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error("Driver not Found " + e);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Connection Failed !" + e);
		}
	}

	public Connection getConnection() {
		try {
			if (this.connection == null) {
				connect();
			} else if (this.connection.isClosed()) {
				connect();
			}
			return this.connection;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("connection Failed ! " + e);
		}

		return null;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.warn("Connection can't be closed " + e);
		}
	}
	// -- for test connection --
	 public static void main(String[] args) {
	 Datasource d = new Datasource();
	 try {
	 System.out.println(d.getConnection().isClosed());
	 } catch (SQLException e) {
	 e.printStackTrace();
	 }
	
	 }
}
