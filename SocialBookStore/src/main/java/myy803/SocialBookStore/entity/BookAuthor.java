package myy803.SocialBookStore.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="bookauthor")
public class BookAuthor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "authorid")
	private int idauthor;
	
	@Column(name= "name")
	private String name;
	
    @ManyToMany
    @JoinTable
    (
        name = "book_author_book",
        joinColumns = @JoinColumn(name = "authorid"),
        inverseJoinColumns = @JoinColumn(name = "bookid")
    )
    private List<Book> books;
	
	public BookAuthor() {}
	
	public BookAuthor(int idauthor, String name) {
		this.idauthor = idauthor;
		this.name = name;
	}
	
	public int getIdauthor() {return idauthor;}

	public void setIdauthor(int idauthor) {this.idauthor = idauthor;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public List<Book> getBooks() {return books;}

	public void setBooks(List<Book> books) {this.books = books;}
}
