package myy803.BookStore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="user")
@IdClass(User.class)
public class User implements Serializable {
	@Id
	@Column(name="username")
	private String username;
	
	@Id
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	private static final long serialVersionUID = 1L;
	
	public User() {};
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;	
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
