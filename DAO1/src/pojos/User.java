package pojos;

public class User {
	
	//Column name as Data Member
	private int id;
	private String password;
	private String name;
	private String email;
	private String city;
	
	//default ctor
	public User() {
		
	}

	//param ctor
	public User(int id, String password, String name, String email, String city) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.city = city;
	}

	//getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", city=" + city
				+ "]";
	}
	
	
	
	
}
