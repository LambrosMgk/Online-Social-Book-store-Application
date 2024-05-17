package myy803.SocialBookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.mapper.BookAuthorMapper;


@Service
public class BookAuthorServiceImpl implements BookAuthorService {

	@Autowired 
	private BookAuthorMapper bookAuthor;
	
	@Override
	public List<BookAuthor> ReturnAuthors() {
		List<BookAuthor> bookAuthors = bookAuthor.findAll();
		return bookAuthors;
	}

	@Override
	@Transactional
	public void BookAuthorSave(BookAuthor bookauthor) {
		bookAuthor.save(bookauthor);
	}

}
