package dao;

import java.sql.SQLException;

import pojos.Voter;

public interface IVoterDao {
	
	Voter authenticateUser(String email,String pwd) throws SQLException;

}
