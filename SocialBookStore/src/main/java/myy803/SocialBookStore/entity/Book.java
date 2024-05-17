package myy803.SocialBookStore.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookid")
	private int idbook;
	
	@Column(name="title")
	private String title;
	
	@Column(name="authorid")
	private int authorid;
	
	@Column(name="description")
	private String description;

	@Column(name="userprofile_id")
	private int userprofileid;
		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")  // This should match the column in the database that holds the foreign key.
    private BookCategory bookCategory;
	
    @ManyToMany
    @JoinTable
    (
        name = "book_author_book",
        joinColumns = @JoinColumn(name = "bookid"),
        inverseJoinColumns = @JoinColumn(name = "authorid")
    )
    private List<BookAuthor> bookAuthors;
	
	@OneToMany(mappedBy = "userprofileid", cascade = CascadeType.ALL)
	private List<UserProfile> requestingUsers;

	
	public Book() {}
	
	public Book( String title, BookCategory bookCategory,List<BookAuthor> bookAuthors,String description) {
		this.title = title;
		this.bookCategory = bookCategory;
		this.bookAuthors = bookAuthors;
		this.description = description;
	}
	
	public Book(int idbook, String title, BookCategory bookCategory,String description) {
		this.idbook = idbook;
		this.title = title;
		this.bookCategory = bookCategory;
		this.description = description;
	}

	
	public void setIdbook(int idbook) {this.idbook = idbook;}
	public int getIdbook() {return idbook;}

	
	public void setTitle(String title) {this.title = title;}
	public String getTitle() {return title;}

	
	public void setBookAuthors(List<BookAuthor> bookAuthors) {this.bookAuthors = bookAuthors;}
	public List<BookAuthor> getBookAuthors() {return bookAuthors;}

	
	public void setBookCategory(BookCategory bookCategory) {this.bookCategory = bookCategory;}
	public BookCategory getBookCategory() {return bookCategory;}

	
	public void setRequestingUsers(List<UserProfile> requestingUsers) {this.requestingUsers = requestingUsers;}
	public List<UserProfile> getRequestingUsers() {return requestingUsers;}

	public int getAuthorid() {return authorid;}
	public void setAuthorid(int authorid) {this.authorid = authorid;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public List<String> getBookAuthorsNames(){
		List<String> bookauthorsnames = new ArrayList<>();
		for (BookAuthor bookAuthor : this.bookAuthors) {
			bookauthorsnames.add(bookAuthor.getName());
		}
		return bookauthorsnames;
	}
	
	public String toString() {
		return "Book with title : " + this.title + " from the user with id " + this.userprofileid;
	}

}
