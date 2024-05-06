package myy803.BookStore.entity;

import java.util.List;

public class Book {
	
	private int idbook;
	private String title;
	private List<BookAuthor> bookAuthors;
	private BookCategory bookCategory;
	private List<Book> bookOffers;
	
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

	public List<Book> getBookOffers() {
		return bookOffers;
	}

	public void setBookOffers(List<Book> bookOffers) {
		this.bookOffers = bookOffers;
	}
	
	public String toString() {
		return "";
	}

}
