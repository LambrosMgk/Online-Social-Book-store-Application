package myy803.SocialBookStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.mapper.BookAuthorMapper;
import myy803.SocialBookStore.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private BookAuthorMapper bookAuthorMapper;
	
	@Override
	public List<BookFormData> findAllBooks() {
		
		List<BookFormData> booksFormData = new ArrayList<>();
		
		List<Book> books = bookMapper.findAll();
		if(books.isEmpty() == false) 
		{
			for (Book book : books ) 
			{
				System.out.println("authors" + book.getBookAuthors().toString());
				BookFormData formBook = new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors(),book.getDescription());
				booksFormData.add(formBook); // list of all books
			}
		}else {
			System.out.println("I did not find any book");
		}
		return booksFormData;
	}
	
	public List<Book> retrieveAllBooks(){
		return bookMapper.findAll();
	}

	@Override
	public Book findBookByid(int idbook) {
		Book book = bookMapper.findByIdbook(idbook);
		return book;
	}

	@Override
	public void saveBook(BookFormData bookFormData,int userProfileid) {
		
		List<BookAuthor> bookAuthors = bookAuthorMapper.findAll();
		List<BookAuthor> newAuthors = new ArrayList<>();
		
		for (BookAuthor bookAuth : bookAuthors) { // an den exw ksanadei auton ton sugrafea tote prosuese ton 
			if(! bookAuthors.contains(bookAuth)) {
				newAuthors.add(bookAuth);
				bookAuth.setBooks(bookMapper.findByauthorid(bookAuth.getIdauthor()));
				bookAuthorMapper.save(bookAuth); // kane save to neo author

			}
			Book book = new Book(bookFormData.getTitle(),bookFormData.getBookCategory(),newAuthors,bookAuth.getIdauthor(),bookFormData.getDescription(),userProfileid);
			bookMapper.save(book);
		}

	}
	

	

}
