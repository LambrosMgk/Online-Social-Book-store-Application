package myy803.BookStore.mapper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import myy803.BookStore.entity.User;


public interface UserMapper extends JpaRepository<User , Integer> { 
	
	Optional<User> findByUsername(String username);
	
	public User findUserByuserid(Integer id);
}
