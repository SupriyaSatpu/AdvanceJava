package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.fetchDBConnection;

import pojos.Customer;

public class CustomerDaoImpl implements ICustomerDao {
	
	private Connection cn;
	private PreparedStatement pst1;
	
	public CustomerDaoImpl() throws ClassNotFoundException, SQLException {
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement("select * from my_customers where email = ? and password = ?");
		System.out.println("Customer DAO created");
	}
	
	//clean up
	public void cleanUp() throws SQLException {
		if(pst1 != null)
			pst1.close();
		if(cn != null)
			cn.close();
		
	}
	

	@Override
	public Customer authenticateCustomer(String email, String password) throws SQLException {
		//set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		
		//execute query
		try (ResultSet rs = pst1.executeQuery()){
			if(rs.next()) {
				return new Customer (rs.getInt(1), rs.getString(2), email, password, rs.getDouble(5), rs.getDate(6));
			}
		}
		return null;
	}

}
