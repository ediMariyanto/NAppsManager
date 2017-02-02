package com.napps.common.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.napps.common.bean.Holliday;
import com.napps.common.connection.Datasource;

public class HollidayController {
	private static org.apache.log4j.Logger log = Logger.getLogger(HollidayController.class);

	private Datasource dbCon = new Datasource();
	private Connection con = null;

	public String getValidationHolliday(Holliday holl) throws Exception {
		try {
			
			File path = new File("D:/Kalacakra/NAppsManager/target/classes/configurationDatabase.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document document = docBuilder.parse(path);
			
			String sql = document.getElementsByTagName("querysql").item(0).getTextContent();
			this.con = this.dbCon.getConnection();
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(holl.gethDate().getTime()));
			ResultSet rs = ps.executeQuery();

			String result = "";
			if (rs.next()) {
				result = "HOLLIDAY";
			}
			this.dbCon.closeConnection();
			return result;

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("connection Error ! "+ex );
		}
		return null;

	}
}
