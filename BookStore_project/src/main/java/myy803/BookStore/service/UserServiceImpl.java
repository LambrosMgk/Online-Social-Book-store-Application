package myy803.BookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import myy803.BookStore.entity.User;
import myy803.BookStore.mapper.UserMapper;
import myy803.BookStore.entity.User;
import myy803.BookStore.entity.UserDetails;

public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
public class UserServiceImpl implements UserDetailsService, UserService{
	
	
	
	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.save(user);
    }

	@Override
	public boolean isUserPresent(User user) {
		User storedUser = userMapper.findByUsername(user.getUsername());
		
		if(storedUser == null)
			return false;
		
		return true; //storedUser.method();
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
