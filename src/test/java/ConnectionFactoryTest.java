import java.sql.Connection;
import java.sql.SQLException;

import utils.ConnectionFactory;

public class ConnectionFactoryTest {

	public static void main(String[] args) throws SQLException {
		/*try {
			Connection conn = null;
			conn = ConnectionFactory.getInstance().makeConnection();
			UserDao userDao = new UserDaoImpl();
			User user = new User();
			user.setUn("fwj");
			user.setPw("lqh");
			user.setCreate_date(new Date(System.currentTimeMillis()));
			userDao.save(conn, user);
			System.out.println("ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		ConnectionFactory cf = ConnectionFactory.getInstance();
		Connection conn = cf.makeConnection();
		System.out.println(conn.getAutoCommit());
	}
}