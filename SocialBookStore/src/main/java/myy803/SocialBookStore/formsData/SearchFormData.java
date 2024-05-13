package myy803.SocialBookStore.formsData;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;

public class SearchFormData {
    
	private String title;
    private BookAuthor author;
    private BookCategory category;

 
    public SearchFormData() {}

    
    public SearchFormData(String title, BookAuthor author, BookCategory category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    
    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public BookAuthor getAuthor() {return author;}

    public void setAuthor(BookAuthor author) {this.author = author;}

    public BookCategory getCategory() {return category;}

    public void setCategory(BookCategory category) {this.category = category;}

    @Override
    public String toString() {
        return "SearchFormData{" +"title= " + this.title + '\'' +", author='" + this.author.toString() + '\'' +", category='" + this.category.toString() + '\'' + '}';
    }
}
