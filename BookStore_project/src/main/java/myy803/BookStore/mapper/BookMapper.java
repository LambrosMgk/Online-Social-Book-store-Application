package myy803.BookStore.mapper;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.BookStore.entity.Book;


public interface BookMapper extends JpaRepository<Book , Integer> {
	public List<Book> findByTitle(String title);
	public List<Book> findByTitleContaining(String title);
}
