package myy803.BookStore.entity;

import java.util.List;

public class Book {
	private int idbook;
	private String title;
	private List<BookAuthor> bookAuthors;
	private BookCategory bookCategory;
	private List<Book> bookOffers;

}
