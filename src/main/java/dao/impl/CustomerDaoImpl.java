package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import entity.Customer;
import utils.ConnectionFactory;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> getPageResult(int page,int pageSize) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		int currentPage = pageSize * (page - 1);
		String sql = "SELECT customer_id,first_name,last_name,email,customer.last_update,address FROM customer,address WHERE customer.address_id=address.address_id limit "+currentPage+","+pageSize;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Customer> list = new ArrayList<Customer>();
		Customer customer = null;
		while(rs.next()){
			customer = new Customer();
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setFirst_name(rs.getString("first_name"));;
			customer.setLast_name(rs.getString("last_name"));
			customer.setAddress(rs.getString("address"));
			customer.setEmail(rs.getString("email"));
			customer.setLast_update(rs.getTimestamp("last_update"));
			list.add(customer);
		}
		return list;
	}
	
	public int getCount() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		int result = 0;
		PreparedStatement pstmt = conn.prepareStatement("select count(*) from customer");
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}
		return result;
	}

	@Override
	public Customer queryById(int customer_id) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT customer_id,first_name,last_name,email,last_update,address_id FROM customer WHERE customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer_id);
		ResultSet rs = ps.executeQuery();
		Customer customer = null;
		while(rs.next()){
			customer = new Customer();
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setFirst_name(rs.getString("first_name"));
			customer.setLast_name(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			customer.setLast_update(rs.getTimestamp("last_update"));
			customer.setAddress_id(rs.getInt("address_id"));
		}
		return customer;
	}

	@Override
	public boolean save(Customer customer) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "INSERT INTO customer(first_name,last_name,email,last_update,address_id,store_id,create_date) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customer.getFirst_name());
		ps.setString(2, customer.getLast_name());
		ps.setString(3, customer.getEmail());
		ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
		ps.setInt(5, customer.getAddress_id());
		ps.setInt(6, 1);
		ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
		boolean flag = false;
		if(ps.executeUpdate() != 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delete(int customer_id) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "DELETE FROM customer WHERE customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer_id);
		boolean flag = false;
		if(ps.executeUpdate() != 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean update(Customer customer) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConnection();
		String sql = "UPDATE customer SET first_name=?,last_name=?,email=?,address_id=?,last_update=?,customer_id=?,store_id=?,create_date=? WHERE customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customer.getFirst_name());
		ps.setString(2, customer.getLast_name());
		ps.setString(3, customer.getEmail());
		ps.setInt(4, customer.getAddress_id());
		ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		ps.setInt(6, customer.getCustomer_id());
		ps.setInt(7, 1);
		ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
		ps.setInt(9, customer.getCustomer_id());
		boolean flag = false;
		if(ps.executeUpdate() != 0){
			flag = true;
		}
		return flag;
	}
}