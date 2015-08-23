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

public class CustomerDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		CustomerDao customerDao = new CustomerDaoImpl();
		try {
			PrintWriter out = response.getWriter();
			String msg = "";
			if(customerDao.delete(customer_id)){
				msg = "删除客户成功！";
			}else{
				msg = "删除客户失败！";
			}
			out.println("<script>alert('"+msg+"');window.location='CustomerShow?page=1';</script>");
			//response.sendRedirect("FilmShow");
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
