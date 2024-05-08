package myy803.BookStore.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import myy803.BookStore.entity.UserProfile;


@Repository
public interface UserProfileMapper extends JpaRepository<UserProfile, Integer> {
	public UserProfile findByUsername(String username);
}
