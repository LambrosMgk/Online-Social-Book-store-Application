package myy803.SocialBookStore.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.UserProfile;
import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.formsData.RecommendationsFormData;
import myy803.SocialBookStore.formsData.SearchFormData;
import myy803.SocialBookStore.formsData.UserProfileFormData;
import myy803.SocialBookStore.mapper.BookMapper;
import myy803.SocialBookStore.mapper.UserProfileMapper;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileMapper userProfileMapper;
	@Autowired 
	private BookMapper bookMapper;
	@Autowired
	private SearchStrategy searchStrategy;
	@Autowired
	private RecommendationsFactory recommendationsFactory;
	
	
	
	@Override
	public UserProfileFormData retreiveProfile(String username) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		UserProfileFormData userProfileFormData = new UserProfileFormData(userProfile);
		System.out.println("This is the userprofile object which will be returned " + userProfileFormData.toString());
		return userProfileFormData;
	}

	@Override
	public void save(UserProfileFormData userProfileFormData) {
		int userProfileId = userProfileFormData.getUser_id();
		UserProfile userProfile = userProfileMapper.findByuserid(userProfileId);
		userProfileMapper.save(userProfile);
	
	}

	@Override
	public List<BookFormData> retreiveBookOffers(String username) 
	{
        UserProfile userProfile = userProfileMapper.findByUsername(username);

        List<Book> bookOffers = userProfile.getBookOffers();

        // Convert each Book object into a corresponding BookFormData object
        List<BookFormData> bookFormDataList = new ArrayList<>();
        for (Book book : bookOffers) {
            BookFormData bookFormData = new BookFormData();
            bookFormData.setIdbook(book.getIdbook());
            bookFormData.setTitle(book.getTitle());
            bookFormData.setBookCategory(book.getBookCategory());
            bookFormData.setBookAuthors(book.getBookAuthors());
            bookFormDataList.add(bookFormData);
        }
        return bookFormDataList;
    }
	
	@Override
	public void addBookOffer(String username, BookFormData bookFormData) 
	{
		
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		
		if(userProfile !=null) 
		{
			Book newBookOffer = new Book();
			newBookOffer.setTitle(bookFormData.getTitle());
			List<Book> bookOffers = userProfile.getBookOffers();
			bookOffers.add(newBookOffer);
			userProfileMapper.save(userProfile); // save or update 
		}else 
		{
			System.out.println("user profile not Found dor username: "+ username);
		}
		
		
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
	public void requestBook(int bookid, String username) 
	{
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		
		if(userProfile != null) 
		{
			Book requestedBook = bookMapper.findById(bookid);
			if(requestedBook != null) {
				if(!userProfile.getBooksRequested().contains(requestedBook)) {
					userProfile.getBooksRequested().add(requestedBook);
					userProfileMapper.save(userProfile);
					
					System.out.println("Book with ID "+ requestedBook.getIdbook()+ "requested successfull");
				}else {
					System.out.println("Book with ID "+ requestedBook.getIdbook()+ "is allready requested");
				}
			}else {
				System.out.println("Book not found");
			}
		}else {
			System.out.println("User profile not found for username: " + username);
		}
	}

	@Override
	public List<BookFormData> retrieveBookRequests(String username) 
	{
		List<BookFormData> bookRequests = new ArrayList<>();
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		if(userProfile != null) 
		{
			List<Book> bookrequested = userProfile.getBooksRequested();
			
			
			for (Book book: bookrequested) 
			{
				BookFormData bookFormdata = new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors());
				
				bookRequests.add(bookFormdata);
			}
			
		}else {
			System.out.println("User profile not found for username: " + username);
		}
		return bookRequests;
	}

	@Override
	public List<UserProfileFormData> retrieveRequestingUsers(int bookid) {
		
		List<UserProfileFormData> requestingUsers = new ArrayList<>();
		
		Book book = bookMapper.findById(bookid);
		if(book!=null) 
		{
			List<UserProfile> userProfiles = book.getRequestingUsers();
			
			for(UserProfile userProfile : userProfiles) {
				UserProfileFormData userProfileFormdata = new UserProfileFormData(userProfile);
				requestingUsers.add(userProfileFormdata);
			}
		}else {
			System.out.println(" Book not found ");
		}
				
		return requestingUsers;
	}

	@Override
	public void deleteBookOffer(String username, int bookid) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		
		if(userProfile != null) {
			List<Book> bookOffers = userProfile.getBookOffers();
			for (Iterator<Book> iterator = bookOffers.iterator(); iterator.hasNext();) 
			{
				Book book = iterator.next();
				if(book.getIdbook() == bookid) 
				{
					iterator.remove();
					break;
				}
			}
			userProfileMapper.save(userProfile);
		}else {
			System.out.println("User profile not found");
		}
	}

	@Override
	public void deleteBookRequest(String username, int bookid) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);		
		
		if(userProfile!= null) 
		{
	        List<UserProfile> requestingUsers = bookMapper.findById(bookid).getRequestingUsers();
	        	
	        for (Iterator<UserProfile> iterator = requestingUsers.iterator(); iterator.hasNext();) {
	            UserProfile requestingUser = iterator.next();
	            if (requestingUser.getId_user() == userProfile.getId_user()) {
	                iterator.remove(); 
	                break; 
	            }
	        }
	        bookMapper.save(bookMapper.findById(bookid));
	    } else {
	        System.out.println("User profile not found for username: " + username);
	    }
		
	}

}
