package myy803.SocialBookStore.formsData;


import java.util.List;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;
import myy803.SocialBookStore.entity.User;
import myy803.SocialBookStore.entity.UserProfile;

public class UserProfileFormData {
	
	private int user_id;
	private String username;
	private String fullname;
	private String address;
	private int age;
	private int phonenum;
    private List<BookAuthor> favoriteAuthors;
    private List<BookCategory> favoriteCategories;
    private List<Book> bookOffers;
    private List<Book> requestedBooks;
	

	public UserProfileFormData(UserProfile userProfile) 
	{
		this.user_id = userProfile.getId_user();
		this.username = userProfile.getUsername();
		this.fullname = userProfile.getFullname();
		this.address = userProfile.getAddress();
		this.age = userProfile.getAge();
		this.phonenum =userProfile.getPhonenumber();
		this.favoriteAuthors = userProfile.getFavouriteBookAuthors();
		this.favoriteCategories = userProfile.getFavouriteBookCategories();
		this.bookOffers = userProfile.getBookOffers();
		this.requestedBooks = userProfile.getBooksRequested(); 
	}
	
	
	public int getUser_id() {return user_id;}

	public String getUsername() {return username;}

	public String getFullname() {return fullname;}

	public String getAddress() {return address;}

	public int getAge() {return age;}

	public int getPhonenum() {return phonenum;}
	
    public List<BookAuthor> getFavoriteAuthors() {return favoriteAuthors;}

    public void setFavoriteAuthors(List<BookAuthor> favoriteAuthors) {this.favoriteAuthors = favoriteAuthors;}

    public List<BookCategory> getFavoriteCategories() {return favoriteCategories;}

    public void setFavoriteCategories(List<BookCategory> favoriteCategories) {this.favoriteCategories = favoriteCategories;}

    public List<Book> getBookOffers() {return bookOffers;}

    public void setBookOffers(List<Book> bookOffers) {this.bookOffers = bookOffers;}
    
	public List<Book> getRequestedBooks() {return requestedBooks;}

	public void setRequestedBooks(List<Book> requestedBooks) {this.requestedBooks = requestedBooks;}
	
	@Override
	public String toString() {
		return "UserProfileFormData [user_id=" + this.user_id + ", username=" + this.username + ", fullname=" + this.fullname
				+ ", address=" + this.address + ", age=" + this.age + ", phonenum=" + this.phonenum + "]";
	}
}
	
	
	
	