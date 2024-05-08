package myy803.BookStore.service;

import org.springframework.stereotype.Service;
import myy803.BookStore.entity.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public User findById(String username);
}
