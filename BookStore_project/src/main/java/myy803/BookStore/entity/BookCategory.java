package myy803.BookStore.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name ="bookcategory")
public class BookCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "category")
	private int category; 
	
	@Column(name = "name")
	private String name; 
	
	@OneToMany(targetEntity=Book.class, mappedBy="idbook", fetch=FetchType.LAZY) 
	private List<Book> books;
	
	public BookCategory() {}
	
	public BookCategory(int category, String name) {
		this.category = category;
		this.name = name;
	}
	
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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
