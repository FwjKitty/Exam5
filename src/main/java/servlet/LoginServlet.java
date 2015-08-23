package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import entity.Customer;
import utils.ConnectionFactory;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String first_name = request.getParameter("un");
		String last_name = request.getParameter("pw");
		if(first_name != null){
			Connection conn = ConnectionFactory.getInstance().makeConnection();
			try {
				System.out.println(conn.getAutoCommit());
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			String sql = "SELECT * FROM customer WHERE first_name=? AND last_name=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, first_name);
				ps.setString(2, last_name);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					CustomerDao customerDao = new CustomerDaoImpl();
					try {
						List<Customer> list = customerDao.getPageResult(1,10);
						int count = customerDao.getCount();
						request.getSession().setAttribute("first_name", first_name);
						request.setAttribute("list", list);
						request.setAttribute("count", String.valueOf(count));
						request.setAttribute("page", "1");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						//response.sendRedirect("index.jsp");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				PrintWriter out = response.getWriter();
				out.println("<script>alert('登录失败，用户名错误，请重新登录！');history.go(-1);</script>");
				out.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}