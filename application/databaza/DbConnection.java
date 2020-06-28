package application.databaza;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private static Connection dbConnection;

	private final static String host = "localhost";
	private final static String dbName = "aeroporti";
	private final static String username = "root";
	private final static String password = "";

	public static Connection getConnection() {
		if (dbConnection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				dbConnection = DriverManager.getConnection(
						"jdbc:mysql://" + host + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true", username,
						password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dbConnection;
	}
}
