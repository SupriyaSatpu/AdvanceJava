package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Book;



public interface IBookDao {

	//add a method to fetch distinct categories
	List<String> getAvailableCategories()throws SQLException;
	List <Book> getBookByCategory(String CategoryName)throws SQLException;
	Book getBookDetails(int bookId)throws SQLException;
}
