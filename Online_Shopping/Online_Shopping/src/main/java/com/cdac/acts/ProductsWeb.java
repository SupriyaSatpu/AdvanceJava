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
 * Servlet implementation class ProductsWeb
 */
@WebServlet("/ProductsWeb")
public class ProductsWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
		out.println("<html>");
		out.println("<body>");
		out.println("<table border = '1'>");
		out.println("<tr>");
		out.println("<th>Product id </th>");
		out.println("<th>Product Name </th>");
		out.println("<th>Category Name </th>");
		out.println("</tr>");
		
		
		Integer categoryId = Integer.parseInt(request.getParameter("categoryid"));
		
		
		Connection dbConnection = null;
		PreparedStatement psProduct = null;
		ResultSet rsProduct = null;
		
		
		try {
			//step1 - load driver 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//step2 - establisg connection
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshopping", "root", "1234");
			
			//step3 - create statement
			psProduct = dbConnection.prepareStatement("select * from Products where categoryid=?");
			psProduct.setInt(1, categoryId);
			
			//step4 - excecute query
			rsProduct = psProduct.executeQuery();
			
			//step5 - process result
			while(rsProduct.next()) {
				out.println("<tr>");
				out.println("<td>" + rsProduct.getInt("productid") + "</td>");
				out.println("<td>" + rsProduct.getString("productname") + "</td>");
				out.println("<td>" + rsProduct.getInt("categoryid") + "</td>");
				out.println("</tr>");		
			}
			
			out.println("</table>");  
	        out.println("</body>");  
	        out.println("</html>");  
			
			
		}catch (ClassNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } finally {  
            try {  
                if(rsProduct != null)  
                	rsProduct.close();  
                if(psProduct != null)  
                	psProduct.close();  
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
