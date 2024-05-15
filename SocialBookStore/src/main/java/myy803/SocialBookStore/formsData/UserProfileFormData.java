package myy803.SocialBookStore.formsData;


import java.util.List;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;
import myy803.SocialBookStore.entity.UserProfile;

public class UserProfileFormData {
	
	private int userprofile_id;
	private int user_id;
	private String username;
	private String fullname;
	private String address;
	private int age;
	private int phonenum;
	private List<BookCategory> favoriteCategories;
    private List<BookAuthor> favoriteAuthors;
    private List<Book> bookOffers;
    private List<Book> requestedBooks;
	

	public UserProfileFormData(UserProfile userProfile) 
	{
		this.userprofile_id = userProfile.getUserprofile_id();
		this.user_id = userProfile.getUserid();
		this.username = userProfile.getUsername();
		this.fullname = userProfile.getFullname();
		this.address = userProfile.getAddress();
		this.age = userProfile.getAge();
		this.phonenum = userProfile.getPhonenumber();
		this.favoriteAuthors = userProfile.getFavouriteBookAuthors();
		this.favoriteCategories = userProfile.getFavouriteBookCategories();
		this.bookOffers = userProfile.getBookOffers();
		this.requestedBooks = userProfile.getBooksRequested(); 
	}
	
	/* This constructor should be used to help create a profile for a guest without needing to import the entity userProfile*/
	public UserProfileFormData() 
	{
		this.userprofile_id = -1;
		this.user_id = -1;
		this.username = null;
		this.fullname = null;
		this.address = null;
		this.age = 0;
		this.phonenum = 0;
		this.favoriteAuthors = null;
		this.favoriteCategories = null;
		this.bookOffers = null;
		this.requestedBooks = null; 
	}
	
	
	public void setUserprofile_id(int userprofile_id) { this.userprofile_id = userprofile_id; }
	public int getUserprofile_id() {return userprofile_id;}

	
	public void setUser_id(int user_id) {this.user_id = user_id;}
	public int getUser_id() {return user_id;}
	
	
	public void setUsername(String username) { this.username = username; }
	public String getUsername() {return username;}

	
	public void setFullname(String fullname) { this.fullname = fullname; }
	public String getFullname() {return fullname;}

	
	public void setAddress(String address) { this.address = address; }
	public String getAddress() {return address;}

	
	public void setAge(int age) { this.age = age; }
	public int getAge() {return age;}

	
	public void setPhonenum(int phonenum) { this.phonenum = phonenum; }
	public int getPhonenum() {return phonenum;}
	
	
	public void setFavoriteAuthors(List<BookAuthor> favoriteAuthors) { this.favoriteAuthors = favoriteAuthors; } 
    public List<BookAuthor> getFavoriteAuthors() {return favoriteAuthors;}

    
    public void setFavoriteCategories(List<BookCategory> favoriteCategories) {this.favoriteCategories = favoriteCategories;}
    public List<BookCategory> getFavoriteCategories() {return favoriteCategories;}

    
    public void setBookOffers(List<Book> bookOffers) {this.bookOffers = bookOffers;}
    public List<Book> getBookOffers() {return bookOffers;}
    
    
    public void setRequestedBooks(List<Book> requestedBooks) {this.requestedBooks = requestedBooks;}
	public List<Book> getRequestedBooks() {return requestedBooks;}
	
	@Override
	public String toString() {
		return "UserProfileFormData [userprofile_id=" + this.userprofile_id + ", user_id=" + this.user_id + ", username=" + this.username + ", fullname=" + this.fullname
				+ ", address=" + this.address + ", age=" + this.age + ", phonenum=" + this.phonenum + "]";
	}

}
	
	
	
	