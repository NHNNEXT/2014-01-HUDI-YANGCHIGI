import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultServlet extends HttpServlet	{
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	  }
	 @Override  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		 String message = "Hello World";
		 System.out.println("error!!!!");
        request.setAttribute("message", message);
        request.getRequestDispatcher("/page.jsp").forward(request, response);
	 }  
}
