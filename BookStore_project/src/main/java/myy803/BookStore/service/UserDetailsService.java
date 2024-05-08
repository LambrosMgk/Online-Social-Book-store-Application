package myy803.BookStore.service;

import org.springframework.stereotype.Service;

import myy803.BookStore.entity.UserDetails;

@Service 
public interface UserDetailsService {
	public UserDetails loadUserByUsername(String username);
}
