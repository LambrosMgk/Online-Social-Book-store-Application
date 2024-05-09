package myy803.BookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import myy803.BookStore.entity.User;
import myy803.BookStore.mapper.UserMapper;
import myy803.BookStore.mapper.UserProfileMapper;
import myy803.BookStore.entity.UserDetails;
import myy803.BookStore.entity.UserProfile;

public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserProfileMapper userMapperProfile;
	
	
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
	}
	
	@Override
	public User findByUsername(String username) {
		User user = userMapper.findByUsername(username);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
