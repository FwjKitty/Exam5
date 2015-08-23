package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import entity.Customer;

public class CustomerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CustomerDao customerDao = new CustomerDaoImpl();
		Customer customer = null;
		try {
			customer = customerDao.queryById(Integer.parseInt(request.getParameter("customer_id")));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		customer.setCustomer_id(Integer.parseInt(request.getParameter("customer_id")));
		customer.setFirst_name(request.getParameter("first_name"));
		customer.setLast_name(request.getParameter("last_name"));
		customer.setEmail(request.getParameter("email"));
		customer.setLast_update(new Timestamp(System.currentTimeMillis()));
		customer.setAddress_id(Integer.parseInt(request.getParameter("address_id")));
		String msg = "";
		try {
			if(customerDao.update(customer)){
				msg = "更新客户成功！";
			}else{
				msg = "更新客户失败！";
			}
			PrintWriter out = response.getWriter();
			out.println("<script>alert('"+msg+"');window.location='CustomerShow?page=1';</script>");
			//response.sendRedirect("FilmShow");
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}