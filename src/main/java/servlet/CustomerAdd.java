package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import entity.Customer;

public class CustomerAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Customer customer = new Customer();
		customer.setFirst_name(request.getParameter("first_name"));
		customer.setLast_name(request.getParameter("last_name"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress_id(Integer.parseInt(request.getParameter("address_id")));
		CustomerDao filmDao = new CustomerDaoImpl();
		try {
			PrintWriter out = response.getWriter();
			if(filmDao.save(customer)){
				out.write("<script>alert('新增客户成功！');window.location='CustomerShow?page=1';</script>");
				//response.sendRedirect("FilmShow");
			}else{
				out.println("<script>alert('新增客户失败，请重新添加！');history.go(-1);</script>");
			}
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}