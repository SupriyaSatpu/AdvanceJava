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
import java.sql.SQLException;

/**
 * Servlet implementation class AdminCategoryWeb
 */
@WebServlet("/AdminCategoryWeb")
public class AdminCategoryWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection dbConnection = null;
	    PreparedStatement psGetCategory = null;
	    ResultSet resultCategory = null;

	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<body>");
	    out.println("<a href=\"AddCategory.html\">\r\n"
	    		+ "    <button>Add category</button>\r\n"
	    		+ "</a>");
	    out.println("<table border='1'>");
	    out.println("<tr>");
	    out.println("<th>id</th>");
	    out.println("<th>Category Name</th>");
	    out.println("<th>Category Description</th>");
	    out.println("<th>Image url</th>");
	    out.println("<th>Action</th>");
	   
	    out.println("</tr>");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    
	        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping","root","1234");
	    
	        psGetCategory = dbConnection.prepareStatement("select * from category");
	    
	        resultCategory = psGetCategory.executeQuery();
	    
	        while(resultCategory.next()) {
	            out.println("<tr>");
	            out.println("<td>" + resultCategory.getInt("id") + "</td>"); 
	            out.println("<td><a href='ProductsWeb?categoryid="+resultCategory.getInt("id")+"'> "+resultCategory.getString("name")+"</a></td>"); 
	            out.println("<td>" + resultCategory.getString("description") + "</td>"); 
	            out.println("<td> <img src='Images"+ resultCategory.getString("image") +"' height='60px' width='60px'/> </td>");
	            out.println("<td><a href = '#'>Update</a></td>");
	            out.println("</tr>");  

	        } 
	        out.println("</table>");  
	        out.println("</body>");  
	        out.println("</html>");  

	        } catch (ClassNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (SQLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if(resultCategory != null)  
	                    resultCategory.close();  
	                if(psGetCategory != null)  
	                    psGetCategory.close();  
	                if(dbConnection != null)  
	                    dbConnection.close();  
	            } catch (SQLException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
