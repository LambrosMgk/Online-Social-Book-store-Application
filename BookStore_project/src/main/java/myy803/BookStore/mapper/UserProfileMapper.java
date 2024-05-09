package myy803.BookStore.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import myy803.BookStore.entity.UserProfile;


public interface UserProfileMapper extends JpaRepository<UserProfile, Integer> {
	public UserProfile findByUsername(String username);
}
