package myy803.BookStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	private int id_user;
	private String user_name;
	//private Role role;
	
	private List<BookAuthor> favouriteBookAuthors;
	private List<BookCategory> favouriteBookCategories;
	private List<Book> bookOffers;
	
	
	public User() {};
	
	public User(int id_user, String user_name) {
		this.id_user = id_user;
		this.user_name = user_name;	
	}
	
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
	
	
