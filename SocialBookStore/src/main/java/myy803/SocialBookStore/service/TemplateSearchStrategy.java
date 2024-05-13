package myy803.SocialBookStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.formsData.SearchFormData;
import myy803.SocialBookStore.mapper.BookMapper;

@Service
public abstract class TemplateSearchStrategy implements SearchStrategy {
	
	@Autowired 
	protected BookMapper bookMapper;

	@Override
	public List<BookFormData> search(SearchFormData searchFormData, BookMapper bookMapper) {
		
		List<Book> initialBooks = makeinitialListOfBooks(searchFormData); //Retrieve initial list of books
		List<Book> filteredBooks = new ArrayList<>(); //new list to return the books
		for (Book book: initialBooks) 
		{
			if(checkIfAuthorsMatch(searchFormData,book)) 
			{
				filteredBooks.add(book);
			}
		}
		return null;
	}
	
	protected abstract List<Book> makeinitialListOfBooks(SearchFormData searchDto);
	
	protected abstract boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book);
	
	private List<BookFormData> convertToBookFormData(List<Book> books)
	{
//		List<BookFormData> bookFormData = new ArrayList<>();
//		for (Book book: books) 
//		{
//		
//		}
		return null;
	}
	
}
