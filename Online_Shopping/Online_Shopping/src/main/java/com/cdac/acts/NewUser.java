package com.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		Connection dbConnection = null;
		PreparedStatement psAuthenticationUser = null;
		ResultSet resultAunthenticate = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping","root","1234");
			psAuthenticationUser = dbConnection.prepareStatement("INSERT INTO user (username, password) VALUES (?,?)");
			
		
			psAuthenticationUser.setString(1,username);
			psAuthenticationUser.setString(2,password);
			
			
			int rows = psAuthenticationUser.executeUpdate();
			
			
			if(rows != 0) {
				out.println("Successfully");
				response.sendRedirect("login.html");
			}
			else {
				out.println("Something went wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(resultAunthenticate!=null)
				resultAunthenticate.close();
			if(psAuthenticationUser!=null)
				psAuthenticationUser.close();
			if(dbConnection!=null)
				dbConnection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	}


