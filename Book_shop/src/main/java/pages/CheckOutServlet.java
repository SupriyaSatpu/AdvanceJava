package pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/check_out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set resp content type
				response.setContentType("text/html");
				// get PrintWriter to send resp to a clnt
				try (PrintWriter pw = response.getWriter()) {
					//1. Get HttpSession WC
					HttpSession sess=request.getSession(); 
					//get customer details from the session scope
					Customer cust=(Customer) sess.getAttribute("customer_dtls");
					if(cust != null) {
						pw.print("<h4>Hello,"+cust.getName()+"</h4>");
						List<Integer> bookCart = (List<Integer>)sess.getAttribute("cart");
						BookDaoImpl bookDao = (BookDaoImpl)sess.getAttribute("book_dao");
						pw.print("<h3>Cart Contents</h3>");
						double total = 0;
						for(int id:bookCart) {
							Book book= bookDao.getBookDetails(id);
							pw.print("<h4>"+book+"</h4>");
							total += book.getPrice();
						}
						pw.print("<h4>Total Cart Value" + total+ "</h4>");
					}	
					 else // no : err mesg : session tracking failed!!!!
						pw.print("<h4> Session Tracking Failed : No Cookies!!!!!!</h4>");
					sess.invalidate();
					pw.print("<h4> You have logged out.....</h4>");// send mesg "You have logged out...."
					pw.print("<h4> <a href='login.html'>Visit Again</a></h4>");// Send a link : "Visit Again" : login form

				}catch(Exception e) {
					throw new ServletException("err in do-get of" + getClass().getName(),e);
				}

	}

}
