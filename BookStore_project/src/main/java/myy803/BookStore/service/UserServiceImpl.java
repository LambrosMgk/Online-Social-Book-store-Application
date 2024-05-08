package myy803.BookStore.service;

import myy803.BookStore.entity.User;
import myy803.BookStore.entity.UserDetails;

public class UserServiceImpl implements UserDetailsService, UserService{
	
	
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserPresent(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
