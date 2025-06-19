package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Book;
import pojos.Customer;

import static utils.DBUtils.fetchDBConnection;

public class BookDaoImpl implements IBookDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3;
	
	public BookDaoImpl() throws ClassNotFoundException, SQLException {
		
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement("select distinct category from dac_books");
		pst2 = cn.prepareStatement("select * from dac_books where category = ?");
		pst3 = cn.prepareStatement("select * from dac_books where id = ?");
		System.out.println("Customer DAO created");
		
	}
	
	@Override
	public Book getBookDetails(int bookId) throws SQLException {
		pst3.setInt(1,bookId);
		try(ResultSet rst = pst3.executeQuery()){
			if(rst.next()) {
				return new Book (bookId, rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(4));
			}
		}
		return null;
	}

		//clean up
		public void cleanUp() throws SQLException {
			if(pst1 != null)
				pst1.close();
			if(pst2 != null)
				pst2.close();
			if(pst3 != null)
				pst3.close();
			if(cn != null)
				cn.close();
			
		}
	
	@Override
	public List<String> getAvailableCategories() throws SQLException {
		List<String>categories = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()){
			while(rst.next()) {
				categories.add(rst.getString(1));
			}
		}
		
		
		
		return categories;
	}

	@Override
	public List<Book> getBookByCategory(String CategoryName) throws SQLException {
		List<Book> books = new ArrayList<>();
		// set IN param : category
		pst2.setString(1, CategoryName);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next())
				books.add(new Book(rst.getInt(1), rst.getString(2), rst.getString(3), CategoryName, rst.getDouble(5)));
		}
		System.out.println("dao reted books "+books);
		return books;

	}
	

	

	
}
