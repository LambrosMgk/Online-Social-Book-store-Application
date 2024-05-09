package myy803.BookStore.service;

import org.springframework.stereotype.Service;

import myy803.BookStore.entity.UserDetails;

@Service
public interface UserProfileService {
	public UserDetails loadUserByUsername(String username);
}
