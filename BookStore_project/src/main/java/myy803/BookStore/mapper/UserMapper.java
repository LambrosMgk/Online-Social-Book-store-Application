package myy803.BookStore.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import myy803.BookStore.entity.User;

@Repository
public interface UserMapper extends JpaRepository<User , Integer> { 
	public void saveUser(User user);
	public User findByUsername(String username);
}
