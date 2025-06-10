package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Custom_exc.UserException;

import static utils.DButils.fetchDBConnection;

import pojos.User;

public class UserDAOImpl implements IUserdao {

	private Connection cn;
	private PreparedStatement ps1;
	private PreparedStatement ps2;
	private PreparedStatement ps3;
	private PreparedStatement ps4;
	private PreparedStatement ps5;

	// ctor: just calls once beginning by the tester
	public UserDAOImpl() throws ClassNotFoundException, SQLException {
		// get connection from DBUtils
		cn = fetchDBConnection();

		// create statement
		ps1 = cn.prepareStatement("SELECT * FROM user WHERE city = ?"); // fetch
		ps2 = cn.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?)"); // Insert
		ps3 = cn.prepareStatement("SELECT * FROM user"); // Display
		ps4 = cn.prepareStatement("UPDATE user SET password = ? WHERE name = ?"); // Update
		ps5 = cn.prepareStatement("DELETE FROM user WHERE id = ?"); // Delete

	}

	@Override
	public List<User> getUserByCity(String city) throws SQLException {
		// will be invoked by tester: once per client request

		// empty user arraylist
		ArrayList<User> userlist = new ArrayList<>();
		// set in param
		ps1.setString(1, city);
		// execution query
		try (ResultSet rs = ps1.executeQuery()) {
			while (rs.next()) {
				userlist.add(
						new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		}
		return userlist;
	}

	@Override
	public String addUser(User obj) throws SQLException {
		ps2.setInt(1, obj.getId());
		ps2.setString(2, obj.getPassword());
		ps2.setString(3, obj.getName());
		ps2.setString(4, obj.getEmail());
		ps2.setString(5, obj.getCity());

		int rowCount = ps2.executeUpdate();
		if (rowCount == 1) {
			System.out.println("Successful");
		} else {
			System.out.println("Unsuccessful");
		}

		return null;
	}

	@Override
	public List<User> getAllUser() throws SQLException {
		// empty user arraylist
		ArrayList<User> userlist = new ArrayList<>();
		// set in param
		// execution query
		try (ResultSet rs = ps3.executeQuery()) {
			while (rs.next()) {
				userlist.add(
						new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		}
		return userlist;
	}

	@Override
	public Boolean updateUser(String name, String password) throws SQLException {
		ps4.setString(2, name);
		ps4.setString(1, password);

		int rowCount = ps4.executeUpdate();
		if (rowCount == 1) {
			System.out.println("Update Successful");
		} else {
			System.out.println("Update unsuccessful");
		}
		return null;
	}

	@Override
	public Boolean deleteUser(int id) throws SQLException {
		ps5.setInt(1, id);

		int rowCount = ps5.executeUpdate();
		if (rowCount == 1) {
			System.out.println("Delete Successful");
		} else {
			System.out.println("Delete Unsuccessful");
		}

		return null;
	}

	// close all DB resources just once i.e end of application
	public void cleanUp() throws SQLException {
		if (ps1 != null) // no statement then ps is null and give nullPointer Exception
			ps1.close();

		if (ps2 != null) //
			ps2.close();

		if (ps3 != null) //
			ps3.close();

		if (ps4 != null) //
			ps4.close();

		if (ps5 != null) //
			ps5.close();

		if (cn != null)
			cn.close();

	}

}
