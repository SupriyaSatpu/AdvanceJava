package pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojos.Book;
import pojos.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.BookDaoImpl;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category_details")
public class CategoryDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String categoryName = request.getParameter("cat");
			pw.print("<h4>Books Under category " + categoryName + "</h4>");
			// 1. Get HttpSession from WC
			HttpSession hs = request.getSession();// Category servlet reqs WC to return either new(EMPTY) HS or
		//get book dao instance from session
			BookDaoImpl bookDao = (BookDaoImpl) hs.getAttribute("book_dao");
			if (bookDao != null) {
				
				//Invoke book dao's method to fetch all books by selecteed category
				List<Book> books =bookDao.getBookByCategory(categoryName);
				//dynamic form generation
				pw.print("<form action='add_to_cart'>");
				//generate chkboxes dynamically
				for(Book b : books)
					//input type='checkbox' , name='bid' value=bookid label title author price
					pw.print("<input type='checkbox' name='bid' value="+b.getBookId()+">"+b+"<br>");
				//submit style button
				pw.print("<input type='submit' value='Add To Cart'>");
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
