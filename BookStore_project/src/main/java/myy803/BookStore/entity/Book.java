package myy803.BookStore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idbook")
	private int idbook;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(targetEntity=BookCategory.class, fetch=FetchType.LAZY)
	private BookCategory bookCategory;
	
	@ManyToMany(targetEntity=BookAuthor.class, mappedBy="idauthor", fetch=FetchType.LAZY) 
	private List<BookAuthor> bookAuthors;
	
	@OneToMany(targetEntity=UserProfile.class, mappedBy="id_user", fetch=FetchType.LAZY) 
	private List<UserProfile> requestingUsers;
	
	public Book() {}
	
	public Book(int idbook, String title, BookCategory bookCategory) {
		this.idbook = idbook;
		this.title = title;
		this.bookCategory = bookCategory;
	}
	
	public int getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<BookAuthor> getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(List<BookAuthor> bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String toString() {
		return "";
	}

}
