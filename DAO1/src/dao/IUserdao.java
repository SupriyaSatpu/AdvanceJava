package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.User;

public interface IUserdao {

	//CRUD method declaration
	//1.fetch data 
	List<User> getUserByCity(String city) throws SQLException;
	
	//2.insert data
	String addUser(User obj) throws SQLException;
	
	//3.Display all User 
	List<User> getAllUser() throws SQLException;
	
	//4.Update user
	Boolean updateUser(String name, String password) throws SQLException;
	
	Boolean deleteUser(int id) throws SQLException;

}
