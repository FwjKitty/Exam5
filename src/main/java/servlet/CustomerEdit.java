package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;	
import dao.impl.CustomerDaoImpl;
import entity.Address;
import entity.Customer;
import utils.ConnectionFactory;

public class CustomerEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		CustomerDao customerDao = new CustomerDaoImpl();
		try {
			Customer customer = customerDao.queryById(customer_id);
			if(customer != null){
				String sql = "SELECT address_id,address FROM address";
				List<Address> list = new ArrayList<Address>();
				Address address;
				try {
					Connection conn = ConnectionFactory.getInstance().makeConnection();
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						address = new Address();
						address.setAddress_id(rs.getInt("address_id"));
						address.setAddress(rs.getString("address"));
						list.add(address);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("customer", customer);
				request.setAttribute("list", list);
				RequestDispatcher rd = request.getRequestDispatcher("customerEdit.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
