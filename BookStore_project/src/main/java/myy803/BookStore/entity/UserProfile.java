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
	private String fullname;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "address")
	private String address;

	@Column(name = "age")
	private int age;
	
	@Column(name = "phonenumber")
	private int phonenumber;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_authors",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "authorid")
    )
	private List<BookAuthor> favouriteBookAuthors;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_categories",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "categoryid")
    )
    private List<BookCategory> favouriteBookCategories;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_book",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "bookid")
    )
    private List<Book> bookOffers;
	
	
	public UserProfile() {};
	
	public UserProfile(int id_user, String username) {
		this.userid = id_user;
		this.username = username;	
	}
	
	// setter _/_ getter 
	public int getId_user() {return userid;}

	public void setId_user(int id_user) {this.userid = id_user;}

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}
	
	public String getFullname() {return fullname;}

	public void setFull_name(String fullname) {this.fullname = fullname;}
	
	public String getAddress() {return address;	}

	public void setAddress(String address) {this.address = address;}
	
	public int getAge() {return age;}

	public void setAge(int age) {this.age = age;}
	
	public int getPhonenumber() {return phonenumber;}

	public void setPhonenumber(int phonenumber) {this.phonenumber = phonenumber;}

	public List<BookAuthor> getFavouriteBookAuthors() {return favouriteBookAuthors;}

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