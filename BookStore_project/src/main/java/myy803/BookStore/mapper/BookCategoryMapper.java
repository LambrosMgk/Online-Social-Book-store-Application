package myy803.BookStore.mapper;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import myy803.BookStore.entity.BookCategory;


public interface BookCategoryMapper extends JpaRepository<BookCategory, Integer> {
	public List<BookCategory> findByName(String name);
}

