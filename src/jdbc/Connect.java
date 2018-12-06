package jdbc;

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
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=BUISON-PASONKON\\SQLEXPRESS;databaseName=KhachHang;","sa", "sa");
			Statement stmt = conn.createStatement();
			String sql21 = "insert into Table1(Id, TenKH, DiaChi, Luong) values('4', 'MaiThinh', 'Hanoi', '400.2')";
			stmt.executeUpdate(sql21);
			String sql22 = "update Table1 set Luong = Luong + luong * 0.1";
			stmt.executeUpdate(sql22);
			String sql33 = "select Id, TenKH, DiaChi, Luong from Table1";
			ResultSet rs = stmt.executeQuery(sql33);
			stmt.executeQuery(sql33);
			System.out.println("succes");
			while(rs.next()) {
				int id = rs.getInt("Id");
				double luong = rs.getDouble("Luong");
				String s = rs.getString("TenKH");
				String d = rs.getString("DiaChi");
				System.out.println("ID=" + id + "TenKH="+ s + "DiaChi=" + d + "Luong=" + luong);
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
