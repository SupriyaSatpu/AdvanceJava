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
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Connection dbConnection = null;
		PreparedStatement psAuthenticationUser = null;
		ResultSet resultAunthenticate = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping","root","1234");
			psAuthenticationUser = dbConnection.prepareStatement("select * from user where username=? and password=?");
			psAuthenticationUser.setString(1,username);
			psAuthenticationUser.setString(2,password);
			resultAunthenticate = psAuthenticationUser.executeQuery();
			
			if(resultAunthenticate.next()) {
				//out.println("Welcome");
				response.sendRedirect("CategoryWeb");
			}
			else {
				out.println("Invalid");
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
