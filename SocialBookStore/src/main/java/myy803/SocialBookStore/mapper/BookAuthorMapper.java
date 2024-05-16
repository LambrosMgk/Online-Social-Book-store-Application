package myy803.SocialBookStore.mapper;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import myy803.SocialBookStore.entity.BookAuthor;


public interface BookAuthorMapper extends JpaRepository<BookAuthor, Integer> {
	
	public List<BookAuthor> findByName(String Name); // why??
	
	public BookAuthor findByidauthor(int idauthor); // from the id of the author find the 
	
}
