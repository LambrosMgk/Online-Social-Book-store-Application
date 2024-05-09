package myy803.BookStore.mapper;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import myy803.BookStore.entity.BookAuthor;


public interface BookAuthorMapper extends JpaRepository<BookAuthor, Integer> {
	public List<BookAuthor> findByName(String Name);
}
