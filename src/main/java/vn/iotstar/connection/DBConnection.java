package vn.iotstar.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	
	private final String configFile = "C:\\Users\\HP\\eclipse-workspace\\WEB\\src\\main\\java\\vn\\iotstar\\connection\\config.properties";

	public Connection getConnection() throws Exception {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(configFile));
		
		String serverName = properties.getProperty("serverName");
		String dbName = properties.getProperty("dbName");
		String portNumber = properties.getProperty("portNumber");
		String instance = properties.getProperty("instance");
		String userID = properties.getProperty("userID");
		String password = properties.getProperty("password");
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
	public static void main(String[] args) {
		try {
			System.out.println(new DBConnection().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
