package myy803.SocialBookStore.mapper;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.SocialBookStore.entity.Book;


public interface BookMapper extends JpaRepository<Book , Integer> {
	public List<Book> findByTitle(String title);
	public List<Book> findByTitleContaining(String title);
	public Book findByauthorid(int authorid);
	public Book findByIdbook(int idbook);
	
}
