package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import entity.Customer;

public class CustomerShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CustomerDao customerDao = new CustomerDaoImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		try {
			int count = customerDao.getCount();
			List<Customer> list = customerDao.getPageResult(page,10);
			request.setAttribute("page", String.valueOf(page));
			request.setAttribute("count", String.valueOf(count));
			request.setAttribute("list", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}