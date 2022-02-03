package org.billing_system.electricityBillingApp.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.billing_system.electricityBillingApp.model.Bill;
import org.billing_system.electricityBillingApp.model.User;

public class Service {
	DbConnection dbcon=null;
	String[] months={"january","febuary","march","april","may","june","july","august","september","october","november","december"};

	public Service() {
		
		DbConnection.createDatabase();
	}
	public String addReading(int userid, int meterreading, String date) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String result=null;
		int amount;
		int energy;
		int totalconsumption;
		String month;
		String sql="select username from user where userid=?";
		String sql1="select max(totalconsumption) from billrecord where userid=?";
		String sql2="insert into billrecord(amount,energy,totalconsumption,entrydate,month,userid) values(?,?,?,?,?,?)";
		try {
			dbcon=new DbConnection();
			pstmt=dbcon.conn.prepareStatement(sql);
			pstmt.setInt(1,userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pstmt1=dbcon.conn.prepareStatement(sql1);
				pstmt1.setInt(1, userid);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					totalconsumption=rs1.getInt(1);
				}else {
					totalconsumption=0;
				}			
				totalconsumption=totalconsumption + meterreading;
				energy=meterreading;
				if(energy > 100) {
					amount = 100*5+(energy-100)*10;
				}else {
					amount = energy*5;
				}
				String[] monthnum=date.split("/");
				int m=Integer.parseInt(monthnum[1]);
				month=months[m-1];
				pstmt2=dbcon.conn.prepareStatement(sql2);
				pstmt2.setInt(1, amount);
				pstmt2.setInt(2, energy);
				pstmt2.setInt(3, totalconsumption);
				pstmt2.setString(4, date);
				pstmt2.setString(5, month);
				pstmt2.setInt(6, userid);	
				pstmt2.executeUpdate();
				result="data added successfully";
			
			}else {
				result="user not exist";
				System.out.print(" user not exist");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt2 != null) {
				try {
					pstmt2.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt1 != null) {
				try {
					pstmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}	
			}
			if(rs1 != null) {
				try {
					rs1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}	
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(dbcon != null) {
				try {
					dbcon.conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public Bill getBill(int userid, String month) {
		Bill bill=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry="select * from billrecord where userid=? and month=?";
		try {
			dbcon=new DbConnection();
			pstmt=dbcon.conn.prepareStatement(qry);
			pstmt.setInt(1, userid);
			pstmt.setString(2, month);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bill=new Bill();
				String entrydate=rs.getString(5);
				String startdate=entrydate.replace(entrydate.split("/")[0],"01");
				String enddate=entrydate;
				int amount=rs.getInt(2);
				int energy=rs.getInt(3);
				int totalconsuption=rs.getInt(4);
				int startreading=totalconsuption-energy;
				int endreading=totalconsuption;
				
				bill.setStartDate(startdate);
				bill.setEndDate(enddate);
				bill.setTotalAmount(amount);
				bill.setEnergyConsumed(energy);
				bill.setStartReading(startreading);
				bill.setEndReading(endreading);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(dbcon.conn != null) {
				try {
					dbcon.conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bill;
	}
	
	public String addUser(User user) {
		String status = null;
		PreparedStatement pstmt=null;
		String sql="insert into user (username, address) values(?,?)";
		try {
			dbcon=new DbConnection();
			pstmt=dbcon.conn.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getAddress());
			int res=pstmt.executeUpdate();
			if(res != 0) {
				status="user added successfully!!";
			}else {
				status="something went wrong!!";
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}	
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(dbcon.conn!=null) {
					dbcon.conn.close();
				}	
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
			
		return status;
	}
	public String removeUser(int userid) {
		String sql="delete from user where userid=?";
		String status=null;
		PreparedStatement pstmt=null;
		dbcon=new DbConnection();
		try {
			pstmt=dbcon.conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			int res=pstmt.executeUpdate();
			if(res > 0) {
				status="user removed successfully !!";
			}else {
				status="no user found with user id "+userid; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}	
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(dbcon.conn!=null) {
					dbcon.conn.close();
				}	
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}

