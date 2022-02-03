package org.billing_system.electricityBillingApp.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbConnection {
	Connection conn=null;
	static String user="root";
	static String password="root";
	
	public DbConnection() {
		String url="jdbc:mysql://localhost:3306/billing_system?useSSL=false";
//		String user="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
