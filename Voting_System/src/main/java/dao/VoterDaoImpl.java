package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.fetchDBConnection;

import pojos.Voter;

public class VoterDaoImpl implements IVoterDao {
	
	private Connection cn;
	private PreparedStatement pst1;
	
	//ctor: invoke by servlet 
	public VoterDaoImpl() throws ClassNotFoundException, SQLException {
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement("selecc * from voters email = ? and password = ?");
		System.out.println("Voter DAO created");
	}
	
	//invoked by servlet: authenrication (once per client request(dopost()))
	@Override
	public Voter authenticateUser(String email, String pwd) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, pwd);
		
		try(ResultSet rst = pst1.executeQuery()){
			if(rst.next()) {
				return new Voter(rst.getInt(1), rst.getString(2), email, pwd, rst.getBoolean(5),rst.getString(6));
			}
			
		}
		return null;
	}
	
	public void cleanUp() throws SQLException {
		if(pst1 != null) {
			pst1.close();
		}
		
		if(cn != null) {
			cn.close();
			
			System.out.println("vorer dao cleaned up");
		}
		
		
	}

}
