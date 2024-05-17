package myy803.SocialBookStore.formsData;

import java.util.ArrayList;
import java.util.List;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;
import myy803.SocialBookStore.entity.UserProfile;
public class BookFormData {
    
    private int idbook;
    private String title;
    private BookCategory bookCategory;
    private String nameOfCategory;
    private String description;
	private List<BookAuthor> bookAuthors;
	private String nameOfTheAuthors; 
    private List<UserProfile> requestingUsers;
    
    public BookFormData() {}
    
    public BookFormData(int idbook, String title, BookCategory bookCategory, List<BookAuthor> bookAuthors,String description,
    		List<UserProfile> requestingUsers) {
        this.idbook = idbook;
        this.title = title;
        this.bookCategory = bookCategory;
        this.bookAuthors = bookAuthors;
        this.SetNameofCategory();
        this.description = description;
        this.requestingUsers = requestingUsers;
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
	public String getNameOfTheAuthors() {return nameOfTheAuthors;}
	public void setNameOfTheAuthors(String nameOfTheAuthors) {this.nameOfTheAuthors = nameOfTheAuthors;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
    
	
	public void setRequestingUsers(List<UserProfile> requestingUsers) {this.requestingUsers = requestingUsers;}
	public List<UserProfile> getRequestingUsers() {return requestingUsers;}
	
	
    private  void SetNameofCategory() {this.nameOfCategory = this.bookCategory.getName();}
    
    
    @Override
    public String toString() {
        return "BookFormData [idbook=" + idbook + ", title=" + title + ", bookCategory=" + bookCategory
                + ", bookAuthors=" + bookAuthors + "]";
    }
    
}