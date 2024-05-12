package myy803.BookStore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookid")
	private int idbook;
	
	@Column(name="title")
	private String title;
	
	@Column(name="userid")
	private int userid;
		
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
	
	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL)
	private List<UserProfile> requestingUsers;
	
	public Book() {}
	
	public Book(int idbook, String title, BookCategory bookCategory) {
		this.idbook = idbook;
		this.title = title;
		this.bookCategory = bookCategory;
	}
	
	public int getIdbook() {return idbook;}

	public void setIdbook(int idbook) {this.idbook = idbook;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public List<BookAuthor> getBookAuthors() {return bookAuthors;}

	public void setBookAuthors(List<BookAuthor> bookAuthors) {this.bookAuthors = bookAuthors;}

	public BookCategory getBookCategory() {return bookCategory;}

	public void setBookCategory(BookCategory bookCategory) {this.bookCategory = bookCategory;}

	public String toString() {
		return "Book with title : " + this.title + " from the user with id " + this.userid;
	}

}
