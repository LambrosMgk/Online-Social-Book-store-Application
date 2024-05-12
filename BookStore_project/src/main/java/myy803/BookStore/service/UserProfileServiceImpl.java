package myy803.BookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.BookStore.FormsData.BookFormData;
import myy803.BookStore.FormsData.RecommendationsFormData;
import myy803.BookStore.FormsData.SearchFormData;
import myy803.BookStore.FormsData.UserProfileFormData;
import myy803.BookStore.mapper.BookAuthorMapper;
import myy803.BookStore.mapper.BookCategoryMapper;
import myy803.BookStore.mapper.BookMapper;
import myy803.BookStore.mapper.UserProfileMapper;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileMapper userProfileMapper;
	@Autowired
	private BookAuthorMapper bookAuthorMapper;
	@Autowired 
	private BookCategoryMapper bookCategoriesMapper;
	@Autowired 
	private BookMapper bookMapper;
	@Autowired
	private SearchStrategy searchStrategy;
	@Autowired
	private RecommendationsFactory recommendationsFactory;
	
	
	
	@Override
	public UserProfileFormData retreiveProfile(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserProfileFormData userProfileFormData) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public List<BookFormData> retreiveBookOffers(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBookOffer(String username, BookFormData bookFormData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookFormData> searchBooks(SearchFormData searchFormData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookFormData> recommendBooks(String username, RecommendationsFormData recommendationsFormData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void requestBook(int bookid, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookFormData> retrieveBookRequests(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserProfileFormData> retrieveRequestingUsers(int bookid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookOffer(String username, int bookid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookRequest(String username, int bookid) {
		// TODO Auto-generated method stub
		
	}

}
