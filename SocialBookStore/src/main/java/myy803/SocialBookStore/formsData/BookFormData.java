package myy803.SocialBookStore.formsData;


import java.util.ArrayList;
import java.util.List;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;
import myy803.SocialBookStore.entity.UserProfile;

public class BookFormData {
    
    private int idbook;
    private String title;
    private String nameOfCategory;
    private String description;
    private List<BookCategory> bookCategories;
	private List<BookAuthor> bookAuthors;
    private List<UserProfile> requestingUsers;
    
    public BookFormData() {}
    
    public BookFormData(int idbook, String title, List<BookCategory> bookCategories, List<BookAuthor> bookAuthors,String description,
    		List<UserProfile> requestingUsers) 
    {
        this.idbook = idbook;
        this.title = title;
        this.bookCategories = bookCategories;
        this.bookAuthors = bookAuthors;
        this.description = description;
        this.requestingUsers = requestingUsers;
    }
    
    public BookFormData(int idbook, String title, BookCategory bookCategory, BookAuthor bookAuthor,String description,
    		List<UserProfile> requestingUsers) {
    	List<BookAuthor> bookAuthorList =new ArrayList<>();
    	bookAuthorList.add(bookAuthor);
        this.idbook = idbook;
        this.title = title;
        this.bookCategory = bookCategory;
        this.bookAuthors = bookAuthorList;
        this.SetNameofCategory();
        this.description = description;
        this.requestingUsers = requestingUsers;
    }
    
    public int getIdbook() { return idbook; }
    public void setIdbook(int idbook) { this.idbook = idbook; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public List<BookCategory> getBookCategories() { return bookCategories; }
    public void setBookCategories(List<BookCategory> bookCategories) { this.bookCategories = bookCategories; }
    
    public List<BookAuthor> getBookAuthors() { return bookAuthors; }
    public void setBookAuthors(List<BookAuthor> bookAuthors) { this.bookAuthors = bookAuthors; }
    
    public String getNameOfCategory() {return nameOfCategory;}
	public void setNameOfCategory(String nameOfCategory) {this.nameOfCategory = nameOfCategory;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
    
	
	public void setRequestingUsers(List<UserProfile> requestingUsers) {this.requestingUsers = requestingUsers;}
	public List<UserProfile> getRequestingUsers() {return requestingUsers;}
    
    
    @Override
    public String toString() {
    	String str = "BookFormData [idbook=" + idbook + ", title=" + title
    			+ ", bookCategories=";
    	
    	for(BookCategory x : this.bookCategories)
    		str = str + "," + x.getName();
    	
        str = str + ", bookAuthors=";
        for(BookAuthor x : this.bookAuthors)
        	str = str + "," + x.getName();
        
        str = str + "]";
    	return str;
    }
    
}