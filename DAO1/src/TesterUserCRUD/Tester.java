package TesterUserCRUD;

import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAOImpl;
import pojos.User;

public class Tester {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		try (Scanner sc = new Scanner(System.in)) {
			UserDAOImpl objUser = new UserDAOImpl();
			int choice;
			do {
				System.out.println("User Management System");
				System.out.println("1.Display all user");
				System.out.println("2.Insert User");
				System.out.println("3.Display User based on city");
				System.out.println("4.Update User password based on name");
				System.out.println("5.Delete User based on id");
				
				System.out.println("*******************************");
				System.out.println("Enter your choice");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					objUser.getAllUser().forEach(System.out::println);
					break;
				case 2:
					System.out.println("Enter User Details:");
					System.out.println("ID | Password | Name | Email | City");
					String status = objUser.addUser(new User(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next()));
					System.out.println("User inserted successfully" + status);
					break;
				case 3:
					System.out.println("Enter city");
					String city = sc.next();
					objUser.getUserByCity(city).forEach(System.out::println);
					break;
				case 4:
					System.out.println("Enter user name");
					String name = sc.next();
					System.out.println("Enter new password");
					String password = sc.next();
					objUser.updateUser(name, password);
					break;
				case 5:
					System.out.println("Enter user ID");
					int id = sc.nextInt();
					objUser.deleteUser(id);
					break;
					
				case 0:
					System.out.println("Exit");
					break;
				default:
					System.out.println("Invalid option");

				}
			} while (choice != 0);
		}

		
	}

}
