package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Customer;

public interface CustomerDao {

	public List<Customer> getPageResult(int page,int pageSize) throws SQLException;
	public int getCount() throws SQLException;
	public Customer queryById(int customer_id) throws SQLException;
	public boolean save(Customer customer) throws SQLException;
	public boolean delete(int customer_id) throws SQLException;
	public boolean update(Customer customer) throws SQLException;
}