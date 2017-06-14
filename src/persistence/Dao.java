package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao implements IDao {

	public Connection con;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	private final String URL = "jdbc:mysql://localhost:3306/projeto";
	private final String USER = "root";
	private final String PASSWORD = "estudo";
	
	@Override
	public void open()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	@Override
	public void close()throws Exception{
		con.close();
	}
	
}
