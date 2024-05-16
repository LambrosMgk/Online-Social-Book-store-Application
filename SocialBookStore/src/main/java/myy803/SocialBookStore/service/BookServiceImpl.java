package myy803.SocialBookStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	
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
	

	

}
