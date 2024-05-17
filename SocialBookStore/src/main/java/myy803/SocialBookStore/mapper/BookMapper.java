package myy803.SocialBookStore.mapper;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.UserProfile;


public interface BookMapper extends JpaRepository<Book , Integer> {
	public List<Book> findByTitle(String title);
	public List<Book> findByTitleContaining(String title);
	public List<Book> findByauthorid(int authorid);
	public Book findByIdbook(int idbook);
	public List<Book> findByUserprofileid (int userprofile_id);
}
