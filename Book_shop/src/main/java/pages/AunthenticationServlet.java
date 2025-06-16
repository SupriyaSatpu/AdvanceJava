package pages;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojos.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.CustomerDaoImpl;

/**
 * Servlet implementation class AunthenticationServlet
 */
@WebServlet(description = "User validation servlet", urlPatterns = { "/aunthenticate" }, loadOnStartup = 1)
public class AunthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDaoImpl dao;
	

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {
		try {
			dao = new CustomerDaoImpl();
		}catch(Exception e) {
			throw new ServletException("err in init of"+getClass().getName(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try{
			dao.cleanUp();
		}catch(SQLException e) {
			throw new RuntimeException("err in destroy"+getClass().getName(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			Customer customer = dao.authenticateCustomer(email, password);
			
			if(customer == null) {
				pw.print("<h4>Invalid login!!! please <a href = 'login.html'>Retry</a></h4>");
			}
			else {
				pw.print("<h4>Successful login</h4>");
				//In case of successful login : redirect the client to the next page in the NEXT request coming from
				//client side
				//API of HttpServletResponse
				//public void sendRedirect(String redirectLocation) throws IOException
				response.sendRedirect("category"); //Must match with the URL pattern of the categoryServlet			
				//pw.print("<h5>User details:"+ customer + "</h5>");
			}
		}catch(Exception e) {
			throw new ServletException("err in dopost"+ getClass().getName(), e);
		}
		
	}

}
