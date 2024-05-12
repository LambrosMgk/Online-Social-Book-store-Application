package myy803.SocialBookStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.formsData.RecommendationsFormData;
import myy803.SocialBookStore.formsData.SearchFormData;
import myy803.SocialBookStore.formsData.UserProfileFormData;

@Service
public interface UserProfileService {
	public UserProfileFormData retreiveProfile(String username);
	
	public void save(UserProfileFormData userProfileFormData);
	
	public List<BookFormData> retreiveBookOffers(String username);
	
	public void addBookOffer(String username, BookFormData bookFormData);
	
	public List<BookFormData> searchBooks(SearchFormData searchFormData);
	
	public List<BookFormData> recommendBooks(String username, RecommendationsFormData recommendationsFormData);
	
	public void requestBook(int bookid, String username);
	
	public List<BookFormData> retrieveBookRequests(String username);
	
	public List<UserProfileFormData> retrieveRequestingUsers(int bookid);
	
	public void deleteBookOffer(String username, int bookid);
	
	public void deleteBookRequest(String username, int bookid);
}