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

import entity.Address;
import utils.ConnectionFactory;

/**
 * Servlet implementation class CustomerQueryAddress
 */
public class CustomerQueryAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT address_id,address FROM address";
		List<Address> list = new ArrayList<Address>();
		Address address;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				address = new Address();
				address.setAddress_id(rs.getInt("address_id"));
				address.setAddress(rs.getString("address"));
				list.add(address);
			}
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("customerAdd.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
