package myy803.BookStore.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "categoryid")
	private int categoryid; 
	
	@Column(name = "name")
	private String name; 
	
    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    private List<Book> books;
	
    
  
	public BookCategory() {}
	
	public BookCategory(int categoryid, String name) {
		this.categoryid = categoryid;
		this.name = name;
	}
	
	
	public int getCategoryid() {return categoryid;}

	public void setCategoryid(int category) {this.categoryid = category;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public List<Book> getBooks() {return books;}

	public void setBooks(List<Book> books) {this.books = books;}
}
