package myy803.SocialBookStore.entity;

import java.util.List;
import jakarta.persistence.*;

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
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_requested_books",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "bookid")
    )
    private List<Book> booksRequested;
    
	
	public UserProfile() {};
	
	public UserProfile(int id_user, String username) {
		this.userid = id_user;
		this.username = username;	
	}
	
	public UserProfile(int userid, String fullname, String username, String address, int age, int phonenumber) {
		this.userid = userid;
		this.fullname = fullname;
		this.username = username;
		this.address = address;
		this.age = age;
		this.phonenumber = phonenumber;
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

	public List<Book> getBookOffers() {return bookOffers;}

	public void setBookOffers(List<Book> bookOffers) {this.bookOffers = bookOffers;}
	
	public List<Book> getBooksRequested() {return booksRequested;}

	public void setBooksRequested(List<Book> booksRequested) {this.booksRequested = booksRequested;}

}