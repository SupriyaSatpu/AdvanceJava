package pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet(value = "/test_input",loadOnStartup= 1)
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("In init ");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set resp content type: text/html
		response.setContentType("text/html");
		
		//open PW : to send resp(text), buffered,to the clnt: getWriter()
		try(PrintWriter pw = response.getWriter()){
			
			pw.print(" <h4> Hello " + request.getParameter("f1") +"</h4>");
			
			pw.print("<h4> Chosen Colors " + Arrays.toString(request.getParameterValues("clr")) +"</h4>");
			
			pw.print("<h4> Chosen browser "+ request.getParameter("browser") +"</h4>");
			
			pw.print("<h4> City " + request.getParameter("myselect") +"</h4>");
			
			pw.print("<h4> Clint info " + request.getParameter("info") +"</h4>");
		}
		
		
		
	}

	@Override
	public void destroy() {
		System.out.println("in destroy");
	}

	
}
