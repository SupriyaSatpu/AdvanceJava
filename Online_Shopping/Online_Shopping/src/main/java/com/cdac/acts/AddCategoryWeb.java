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
 * Servlet implementation class AddCategoryWeb
 */
@WebServlet("/AddCategoryWeb")
public class AddCategoryWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String categoryName = request.getParameter("name");
		String  categoryDescription= request.getParameter("description");
		String imageURL = request.getParameter("image");
//		
//		out.println(categoryName);
//		out.println(categoryDescription);
//		out.println(imageURL);
//		
		Connection dbConnection = null;
		PreparedStatement psAddCategory = null;
		ResultSet resultAddCategory = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping","root","1234");
		
			psAddCategory = dbConnection.prepareStatement("INSERT INTO category (name, description, image) VALUES (?,?,?)");
			
		
			psAddCategory.setString(1,categoryName);
			psAddCategory.setString(2,categoryDescription);
			psAddCategory.setString(3,imageURL);
			
			
			int rows = psAddCategory.executeUpdate();
			
			
			if(rows != 0) {
				
				response.sendRedirect("AdminCategoryWeb");
			}
			else {
				out.println("Something went wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(resultAddCategory!=null)
				resultAddCategory.close();
			if(psAddCategory!=null)
				psAddCategory.close();
			if(dbConnection!=null)
				dbConnection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
