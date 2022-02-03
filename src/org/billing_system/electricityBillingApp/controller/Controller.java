package org.billing_system.electricityBillingApp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.billing_system.electricityBillingApp.dao.DbConnection;
import org.billing_system.electricityBillingApp.dao.Service;
import org.billing_system.electricityBillingApp.model.Bill;
import org.billing_system.electricityBillingApp.model.User;

import com.google.gson.Gson;

@WebServlet("/meter/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Service service=null;
    public Controller() {
        service=new Service();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("application/json");
		Gson gson =new Gson();
		String path=request.getPathInfo();
		String[] splitedpath=path.split("/");
		if(splitedpath.length==4) {
			String endpoint=splitedpath[1];
			String userid=splitedpath[2];
			String month=splitedpath[3];
			if(endpoint.equals("get-bill")) {
				Bill bill=service.getBill(Integer.parseInt(userid), month);
				if(bill != null) {
					String res=gson.toJson(bill);
					out.print(res);
					out.flush();
				}else {
					out.print("No record Found !!");
				}
			}else {
				out.print("wrong url endpoint entered !!");
			}
		}else {
			out.append("Served at: ").append(request.getContextPath());
			out.print("please put correct url endpoint to get details!!");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		PrintWriter out=response.getWriter();
		String path=request.getPathInfo();
		String[] splitedpath=path.split("/");
		if (splitedpath[1].equals("add-readings")) {
			StringBuilder buffer=new StringBuilder();
			BufferedReader reader=request.getReader();
			String line;
			while((line=reader.readLine())!=null) {
				buffer.append(line);
			}
			String[] data=buffer.toString().split(" ");
			int userid=Integer.parseInt(data[2].substring(data[2].indexOf('"', 6)+1,data[2].indexOf('-')));
			int meterreading=Integer.parseInt(data[4].substring(data[4].indexOf('"', 6)+1,data[4].indexOf('-')));
			String date=data[6].substring(data[6].indexOf('"', 6)+1,data[6].indexOf('-'));
			
			String status=service.addReading(userid, meterreading, date);
			out.print(status);
		}	
		else if (splitedpath[1].equals("add-user")) {
			User user=null;
			StringBuilder buffer=new StringBuilder();
			BufferedReader reader=request.getReader();
			String line;
			while((line=reader.readLine())!=null) {
				buffer.append(line);
			}
			String[] data=buffer.toString().split(" ");
			String username=data[2].substring(data[2].indexOf('"', 6)+1,data[2].indexOf('-'));
			String address=data[4].substring(data[4].indexOf('"', 6)+1,data[4].indexOf('-'));
			user=new User(username,address);
			String status=service.addUser(user);
			out.print(status);
		}
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		PrintWriter out=response.getWriter();
		String path=request.getPathInfo();
		String[] splitedpath=path.split("/");
		int userid=Integer.parseInt(splitedpath[2]);
		if (splitedpath[1].equals("remove-user")) {
			String status=service.removeUser(userid);
			out.print(status);
		}else {
			out.print("wrong url entered ");
		}
	}
}
