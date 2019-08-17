package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.surpass.realkits.JGecService;

public class DBUtil {
	public static void main(String[] args) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try
		{
		Date d1 = df.parse("2004-03-26 13:31:40");
		Date d2 = df.parse("2019-04-23 10:10:24");
		long diff = d2.getTime();
		System.out.println(System.currentTimeMillis()+"::::::"+diff);
		}
		catch (Exception e)
		{
		}

	}
	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.07:3306/test01?useUnicode=true&characterEncoding=utf8","root","1234");
		return con;
	}

	public static void setClos(Connection con, PreparedStatement ps,
			ResultSet rs) {
		try {
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static JGecService gec() throws Exception{
		
		return new JGecService("geC.dll");
	}

	public static void setClos(Connection con, PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
