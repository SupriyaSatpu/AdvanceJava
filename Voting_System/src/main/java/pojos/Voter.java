package pojos;

public class Voter {
	
	private int userTd;
	private String name,email,password;
	private boolean votingStatus;
	private String role;
	
	
	public Voter() {
		
		// TODO Auto-generated constructor stub
	}


	public Voter(int userTd, String name, String email, String password, boolean votingStatus, String role) {
		super();
		this.userTd = userTd;
		this.name = name;
		this.email = email;
		this.password = password;
		this.votingStatus = votingStatus;
		this.role = role;
	}


	public int getUserTd() {
		return userTd;
	}


	public void setUserTd(int userTd) {
		this.userTd = userTd;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isVotigStatus() {
		return votingStatus;
	}


	public void setVotigStatus(boolean votigStatus) {
		this.votingStatus = votigStatus;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Voter [userTd=" + userTd + ", name=" + name + ", email=" + email + ", votigStatus=" + votingStatus
				+ ", role=" + role + "]";
	}
	

	
}
