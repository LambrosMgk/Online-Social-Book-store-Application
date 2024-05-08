package myy803.BookStore.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="bookauthor")
public class BookAuthor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "idauthor")
	private int idauthor;
	
	@Column(name= "name")
	private String name;
	
    @ManyToMany(mappedBy="bookAuthors")
    private List<Book> books;
	
	public BookAuthor() {}
	
	public BookAuthor(int idauthor, String name) {
		this.idauthor = idauthor;
		this.name = name;
	}
	
	public int getIdauthor() {
		return idauthor;
	}

	public void setIdauthor(int idauthor) {
		this.idauthor = idauthor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
