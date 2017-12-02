package com.ggy.SalesManageSystem.commons;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	static {
		Properties p = new Properties();
		try {
			File file = new File(
					"../SalesManageSystem/src/db.properties");
			p.load(new FileReader(file));
			DRIVER = p.getProperty("DRIVER");
			URL = p.getProperty("URL");
			USER = p.getProperty("USER");
			PASSWORD = p.getProperty("PASSWORD");
			Class.forName(DRIVER);			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DBUtils();
	}

	public DBUtils() {

	}

	/**
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		int result = 0;
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet query(String sql, Object... params) {
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				rs = ps.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
