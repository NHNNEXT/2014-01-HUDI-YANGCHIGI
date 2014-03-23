package org.yangchigi.web;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DefaultServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "Hello World";
		request.setAttribute("message", message);
		System.out.println(request);
		System.out.println("******* WRITE *******");
		Employee empl = new Employee("Jack", "Bauer", new Date(System.currentTimeMillis()), "911");
		empl = save(empl);
		getServletContext().getRequestDispatcher("/page.jsp").forward(request,
				response);
	}

	private static Employee save(Employee employee) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		Long id = (Long) session.save(employee);
		employee.setId(id);

		session.getTransaction().commit();

		session.close();

		return employee;
	}
}
