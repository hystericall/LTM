package bai312dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect {
	private Connection conn;
	private String user = "sa";
	private String password = "sa";

	public Connection getConnectMySQL(String URL) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(URL, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Khong the ket noi CSDL");
		}
		return conn;
	}
	
}


// URL "jdbc:sqlserver://localhost:1433;instance=BUTATU\\SQLEXPRESS;databaseName=KhachHang;"
// Sql Select*from Table1