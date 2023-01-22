package ar.com.ada.backend12.RRHH.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MasterDAO {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/RRHH";
	private static final String USER = "root";
	private static final String PASS = "Pass12345.";
	
	private static Connection conn = null;
	
	public static void openDBConnection() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
	protected static Connection getDBConnection() throws SQLException {
		if (conn == null || (conn != null && conn.isClosed())) {
			openDBConnection();
		}
		return conn;
	}
	
	public static void closeDBConnection() throws SQLException {
		if (conn == null) {
			conn.close();
		}
	}
	
	
	
	
}
