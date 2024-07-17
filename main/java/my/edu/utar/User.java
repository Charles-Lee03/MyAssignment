package my.edu.utar;

public class User {
	private String name, member_type;
	private boolean excl_reward;
	
	public String getName() {
		return name;
	}
	
	public String getMember_type() {
		return member_type;
	}
	
	public boolean getExcl_reward() {
		return excl_reward;
	}
	
	public User(String name, String member_type, boolean excl_reward) {
		this.name = name;
		this.member_type = member_type;
		this.excl_reward = excl_reward;
	}
	
	
}
