package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public static void main(String[] args) {
		try {
			System.out.println("ket noi csdl");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=BUTATU\\SQLEXPRESS;databaseName=users;","sa", "sa");
			Statement stmt = conn.createStatement();
//			String sql21 = "insert into Table1(ID, TenKH, DiaChi, Luong) values('4', 'MaiThinh', 'Hanoi', '400.2')";
//			stmt.executeUpdate(sql21);
//			String sql22 = "update Table1 set Luong = Luong + luong * 0.1";
//			stmt.executeUpdate(sql22);
			String sql33 = "select username from acc";
			ResultSet rs = stmt.executeQuery(sql33);
//			stmt.executeQuery(sql33);
			System.out.println("succes");
			while(rs.next()) {
//				int id = rs.getInt("ID");
//				double luong = rs.getDouble("Luong");
				String s = rs.getString("username");
//				String d = rs.getString("DiaChi");
				System.out.println("ID=" + s);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
