package myy803.BookStore.service;

import org.springframework.stereotype.Service;
import myy803.BookStore.entity.User;
import myy803.BookStore.entity.UserDetails;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public User findByUsername(String username);
    public UserDetails loadUserByUsername(String username);
	public User findById(String username);
}
