package org.billing_system.electricityBillingApp.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbConnection {
	Connection conn=null;
	static String user="root";
	static String password="niraj@123";
	public static void createDatabase() {
		String url="jdbc:mysql://localhost:3306?useSSL=false";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user, password);
			Statement st=con.createStatement();
			String table1="CREATE TABLE IF NOT EXISTS billing_system.`user` (\r\n" + 
					"  `userid` int(3) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `username` varchar(50) DEFAULT NULL,\r\n" + 
					"  `address` varchar(100) DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`userid`)\r\n" + 
					")";
			String table2="CREATE TABLE IF NOT EXISTS billing_system.`billrecord` (\r\n" + 
					"  `billid` int(3) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `amount` int(5) DEFAULT NULL,\r\n" + 
					"  `energy` int(5) DEFAULT NULL,\r\n" + 
					"  `totalconsumption` int(5) DEFAULT NULL,\r\n" + 
					"  `entrydate` varchar(20) DEFAULT NULL,\r\n" + 
					"  `month` varchar(20) DEFAULT NULL,\r\n" + 
					"  `userid` int(3) DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`billid`),\r\n" + 
					"  KEY `userid` (`userid`),\r\n" + 
					"  CONSTRAINT FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
					")";
			int result=st.executeUpdate("CREATE DATABASE IF NOT EXISTS billing_system");
			if(result != 0) {
				System.out.println(" database with name billing_system created !! ");
				st.executeUpdate(table1);
				st.executeUpdate(table2);
//				System.out.println(" both tables created !!");
			}else {
				System.out.println(" database with name niraj already exist !!");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
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
