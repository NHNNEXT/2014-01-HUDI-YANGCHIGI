
package org.yangchigi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserModifyServlet extends HttpServlet {

	public UserModifyServlet() {
		super();
	}

	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse res)
	// throws ServletException, IOException {
	//
	// }

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserModifyServlet > doPost call");
		// String msg = req.getParameter("msg");
		// String pwd = req.getParameter("pwd");
		//
		// res.setContentType("text/html;charset=euc-kr");
		// PrintWriter out =res.getWriter();
		// out.println("<html>");
		// out.println("<p>haha</p>");
		// out.println("<p>param msg: </b>" + msg + "</p>");
		// out.println("<p>param pwd: </b>" + pwd + "</p>");
		// out.println("</html>");

		request.setCharacterEncoding("euc-kr");
		String realFolder = "";
		String filename1 = "";
		int maxSize = 1024 * 1024 * 5;
		String encType = "euc-kr";
		String savefile = "img";
		ServletContext scontext = getServletContext();
		realFolder = "/Users/yurim/Documents/workspace2/2014-01-HUDI-YANGCHIGI/webapp/img";

		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			String file1 = (String) files.nextElement();
			filename1 = multi.getFilesystemName(file1);
			
			System.out.println(file1 + "," + filename1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("fileName", filename1);
		
		
		request.getRequestDispatcher("/upload.jsp").forward(request,
				response);
	}

}