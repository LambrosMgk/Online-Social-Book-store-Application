package myy803.SocialBookStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.SocialBookStore.entity.UserProfile;
import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.formsData.RecommendationsFormData;
import myy803.SocialBookStore.formsData.SearchFormData;
import myy803.SocialBookStore.formsData.UserProfileFormData;

@Service
public interface UserProfileService {
	public UserProfileFormData retreiveProfile(String username);
	
	public UserProfileFormData retreiveProfile(int userid);
	
	public void save(UserProfileFormData userProfileFormData);
		
	public List<BookFormData> retreiveBookOffers(int userprofile_id);
	
	public void addBookOffer(String username, BookFormData bookFormData);
	
	public List<BookFormData> searchBooks(SearchFormData searchFormData);
	
	public List<BookFormData> recommendBooksByCategory(UserProfileFormData userProfileFormData);
	
	public List<BookFormData> recommendBooksByAuthor(UserProfileFormData userProfileFormData);
	
	public void requestBook(int bookid, String username);
	
	public List<BookFormData> retrieveBookRequests(int userprofileid);
	
	public List<UserProfileFormData> retrieveRequestingUsers(int bookid);
	
	public void deleteBookOffer(String username, int bookid);
	
	public void deleteBookRequest(String username, int bookid);

	void UpdateUserProfile(UserProfile userProfile);
}
