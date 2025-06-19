 package pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojos.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.BookDaoImpl;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			pw.print("<h4> Customer login successful</h4>");
			// 1. Get HttpSession from WC
			HttpSession hs = request.getSession();// Category servlet reqs WC to return either new(EMPTY) HS or
		// retrieve customer details from the session scope
			Customer c = (Customer) hs.getAttribute("customer_dtls");
			if (c != null) {
//				pw.print("<h4> Hello," + c.getName()+"</h4>");
//			//get book dao instance from session scope: to lift all categories from db
//				BookDaoImpl dao = (BookDaoImpl)hs.getAttribute("book_dao");
//				//invoke book dao,s method fetch all distinct categories
//				List<String> categories = dao.getAvailableCategories();
//				//dynamic form generation
//				pw.print("<form action = 'category_details'>");
//				pw.print("Coose a category");
//				pw.print("<select name = 'cat'>");
//				for(String s: categories)
//					pw.print("<option value =" + s + ">" +s+"</option>");
//				pw.print("</select><br>");
//				pw.print("<input type = 'submit' value = 'Choose'>");
//				pw.print("</form>");
				
				
				pw.print("<h4> Hello ,  " + c.getName()+"</h4>");//greeting
				//get book dao instance  from session scope : to lift all categories from db
				BookDaoImpl dao=(BookDaoImpl)hs.getAttribute("book_dao");
				//Invoke book dao's method to fetch all distinct categories
				List<String> categories = dao.getAvailableCategories();
				//dynamic form generation
				pw.print("<form action='category_details'>");
				pw.print("Choose a Category ");
				//select option list
				pw.print("<select name='cat'>");
				for(String s : categories)
						pw.print("<option value="+s+">"+s+"</option>");					
				pw.print("</select><br>");
				//submit style button
				pw.print("<input type='submit' value='Choose'>");
				pw.print("</form>");

			}	
			
			else
				pw.print("<h4> Session Tracking Failed : No Cookies!!!!!!</h4>");
			// send a link to clnt : for logout
			pw.print("<h4> <a href='check_out'>Check Out</a></h4>");
			// after clnt clicks on this link : http://host:port/day4.1/check_out (clnt pull
			// I)

		}catch(Exception e) {
			throw new ServletException("err in do-get of" + getClass().getName(),e);
		}
		
	}

	
}
