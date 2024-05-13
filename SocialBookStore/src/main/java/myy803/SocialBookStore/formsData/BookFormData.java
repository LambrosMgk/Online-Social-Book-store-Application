package myy803.SocialBookStore.formsData;

import java.util.List;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;

public class BookFormData {
    
    private int idbook;
    private String title;
    private BookCategory bookCategory;
    private List<BookAuthor> bookAuthors;
    
    public BookFormData() {}
    
    public BookFormData(int idbook, String title, BookCategory bookCategory, List<BookAuthor> bookAuthors) {
        this.idbook = idbook;
        this.title = title;
        this.bookCategory = bookCategory;
        this.bookAuthors = bookAuthors;
    }
    
    public int getIdbook() { return idbook; }
    public void setIdbook(int idbook) { this.idbook = idbook; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BookCategory getBookCategory() { return bookCategory; }
    public void setBookCategory(BookCategory bookCategory) { this.bookCategory = bookCategory; }

    public List<BookAuthor> getBookAuthors() { return bookAuthors; }
    public void setBookAuthors(List<BookAuthor> bookAuthors) { this.bookAuthors = bookAuthors; }
    
    @Override
    public String toString() {
        return "BookFormData [idbook=" + idbook + ", title=" + title + ", bookCategory=" + bookCategory
                + ", bookAuthors=" + bookAuthors + "]";
    }
}
