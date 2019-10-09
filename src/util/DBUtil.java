package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	final private static String ip = "127.0.0.1";
	final static private int port = 3306;
	final static private String database = "test";
	final static private String encoding = "UTF-8";
	final static private String loginName = "root";
	final static private String password = "admin";
	final static private String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s"
			, ip,port,database,encoding);
	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,loginName,password);
	}
	public static void close(Connection c) {
		if(c!=null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
	}
	
	public static String getLoginName() {
		return loginName;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static String getDatabase() {
		return database;
	}
	
	public static int getPort() {
		return port;
	}
}
