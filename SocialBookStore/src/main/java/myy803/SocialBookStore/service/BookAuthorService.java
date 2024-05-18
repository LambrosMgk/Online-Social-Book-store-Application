package myy803.SocialBookStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.formsData.SearchFormData;


@Service
public interface BookAuthorService {
	public List<BookAuthor>  ReturnAuthors();
	
	public BookAuthor findBookAuthorByName(String name,SearchFormData searchFormData);
	
	public void BookAuthorSave(BookAuthor bookauthor);
}
