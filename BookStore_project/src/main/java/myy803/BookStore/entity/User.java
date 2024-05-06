package myy803.BookStore.entity;

import java.util.List;

public class User {
	private String username;
	private String fullname;
	private Role role;
	
	public User(String username, String fullname) {
		this.username = username;
		this.fullname = fullname;	
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
