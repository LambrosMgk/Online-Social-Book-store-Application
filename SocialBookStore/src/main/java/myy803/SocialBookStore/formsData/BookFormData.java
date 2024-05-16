package myy803.SocialBookStore.formsData;

import java.util.ArrayList;
import java.util.List;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;


public class BookFormData {
    
    private int idbook;
    private String title;
    private BookCategory bookCategory;
    private String nameOfCategory;
    private String description;
	private List<BookAuthor> bookAuthors;
    private List<String> nameOfTheAuthors; 
    
    public BookFormData() {}
    
    public BookFormData(int idbook, String title, BookCategory bookCategory, List<BookAuthor> bookAuthors,String description) {
        this.idbook = idbook;
        this.title = title;
        this.bookCategory = bookCategory;
        this.bookAuthors = bookAuthors;
        this.nameOfTheAuthors = this.SetAuthorsOfTheBook();
        this.SetNameofCategory();
        this.description = description;
    }
    
    public int getIdbook() { return idbook; }
    public void setIdbook(int idbook) { this.idbook = idbook; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BookCategory getBookCategory() { return bookCategory; }
    public void setBookCategory(BookCategory bookCategory) { this.bookCategory = bookCategory; }

    public List<BookAuthor> getBookAuthors() { return bookAuthors; }
    public void setBookAuthors(List<BookAuthor> bookAuthors) { this.bookAuthors = bookAuthors; }
    
    public String getNameOfCategory() {return nameOfCategory;}
	public void setNameOfCategory(String nameOfCategory) {this.nameOfCategory = nameOfCategory;}

	public List<String> getNameOfTheAuthors() {return nameOfTheAuthors;}
	public void setNameOfTheAuthors(List<String> nameOfTheAuthors) {this.nameOfTheAuthors = nameOfTheAuthors;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
    
    private  void SetNameofCategory() {
    	this.nameOfCategory = this.bookCategory.getName();
    }
    
    private List<String> SetAuthorsOfTheBook() {
    	
    	List<String> names_of_authors = new ArrayList<>();
    	
    	for (BookAuthor bookAuth : this.bookAuthors) {
    		names_of_authors.add(bookAuth.getName());
    	}
		return names_of_authors;
    }
    
    
    @Override
    public String toString() {
        return "BookFormData [idbook=" + idbook + ", title=" + title + ", bookCategory=" + bookCategory
                + ", bookAuthors=" + bookAuthors + "]";
    }
}
