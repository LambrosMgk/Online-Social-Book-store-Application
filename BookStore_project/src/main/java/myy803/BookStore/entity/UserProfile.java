package myy803.BookStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userprofile")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "fullname")
	private String full_name;
	
	@Column(name = "username")
	private String user_name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "phonenumber")
	private int phonenumber;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_favorite_authors",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "authors")
    )
	private List<BookAuthor> favouriteBookAuthors;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_favorite_categories",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "category")
    )
    private List<BookCategory> favouriteBookCategories;
    
    
	@ManyToMany(mappedBy="userid", fetch=FetchType.LAZY) 
	private List<Book> bookOffers;
	
	
	public UserProfile() {};
	
	public UserProfile(int id_user, String user_name) {
		this.userid = id_user;
		this.user_name = user_name;	
	}
	
	public int getId_user() {
		return userid;
	}

	public void setId_user(int id_user) {
		this.userid = id_user;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<BookAuthor> getFavouriteBookAuthors() {
		return favouriteBookAuthors;
	}

	public void setFavouriteBookAuthors(List<BookAuthor> favouriteBookAuthors) {
		this.favouriteBookAuthors = favouriteBookAuthors;
	}

	public List<BookCategory> getFavouriteBookCategories() {
		return favouriteBookCategories;
	}

	public void setFavouriteBookCategories(List<BookCategory> favouriteBookCategories) {
		this.favouriteBookCategories = favouriteBookCategories;
	}

	public List<Book> getBookOffers() {
		return bookOffers;
	}

	public void setBookOffers(List<Book> bookOffers) {
		this.bookOffers = bookOffers;
	}
}
	
	
