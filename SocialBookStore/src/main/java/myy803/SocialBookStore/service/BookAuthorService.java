package myy803.SocialBookStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.SocialBookStore.entity.BookAuthor;


@Service
public interface BookAuthorService {
	public List<BookAuthor>  ReturnAuthors();
	
	public void BookAuthorSave(BookAuthor bookauthor);
}
