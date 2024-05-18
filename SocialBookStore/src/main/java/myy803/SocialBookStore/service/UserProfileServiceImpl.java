package myy803.SocialBookStore.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;
import myy803.SocialBookStore.entity.Role;
import myy803.SocialBookStore.entity.User;
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
	private UserService userService;
	@Autowired 
	private BookMapper bookMapper;
	
	//Autowired
	private SearchStrategy searchStrategy;
	@Autowired
	private RecommendationsStrategy recommendationsStrategy;
	@Autowired
	private ExactSearchStrategy exactSearch;
	@Autowired
	private ApproximateSearchStrategy approxSearch;
	
	
	
	@Override
	public UserProfileFormData retreiveProfile(String username) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);

		UserProfileFormData userProfileFormData = new UserProfileFormData(userProfile);
		
		//System.out.println("This is the userprofile object which will be returned " + userProfileFormData.toString());
		return userProfileFormData;
	}
	
	@Override 
	public UserProfileFormData retreiveProfile(int userid) {
		UserProfile userProfile = userProfileMapper.findByUserprofileid(userid);		
		UserProfileFormData userProfileFormData = new UserProfileFormData(userProfile);
		return userProfileFormData;
	}

	@Override
	public void save(UserProfileFormData userProfileFormData) {
		UserProfile userProfile = new UserProfile(
				userProfileFormData.getUser_id(),
				userProfileFormData.getUsername(),
				userProfileFormData.getFullname(),
				userProfileFormData.getAddress(),
				userProfileFormData.getAge(),
				userProfileFormData.getPhonenum());
		
		userProfile.setFavouriteBookCategories(userProfileFormData.getFavoriteCategories());
		userProfile.setFavouriteBookAuthors(userProfileFormData.getFavoriteAuthors());
		
		User user = userService.findByUsername(userProfileFormData.getUsername());
		user.setRole(Role.USER);
		
		userService.saveUser2(user);
		userProfileMapper.save(userProfile);
	}

	
	@Override
	public List<BookFormData> retreiveBookOffers(int userprofile_id) 
	{
        UserProfile userProfile = userProfileMapper.findByUserprofileid(userprofile_id);

        List<Book> bookOffers = userProfile.getBookOffers();
        
        // Convert each Book object into a corresponding BookFormData object
        List<BookFormData> bookFormDataList = new ArrayList<>();
        for (Book book : bookOffers) 
        {
            BookFormData bookFormData = new BookFormData(
            		book.getIdbook(), 
            		book.getTitle(), 
            		book.getBookCategory(),
            		book.getBookAuthors(), 
            		book.getDescription(), 
            		book.getRequestingUsers()
            		);
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
	
	// to return a list of books after searching
	@Override
	public List<BookFormData> searchBooks(SearchFormData searchFormData) {
		if("exact".equals(searchFormData.getTypeOfSearch()))
		{
			return exactSearch.search(searchFormData, bookMapper);
		}
		else if("approximate".equals(searchFormData.getTypeOfSearch())) {
			return approxSearch.search(searchFormData, bookMapper);
		}else {
			throw new IllegalArgumentException("Unknown search");
		}
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
			Book requestedBook = bookMapper.findByIdbook(bookid);
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
	public List<BookFormData> retrieveBookRequests(int userprofileid) 
	{
		List<BookFormData> bookRequests = new ArrayList<>();
		UserProfile userProfile = userProfileMapper.findByUserprofileid(userprofileid);
		
		if(userProfile == null)	// This "if" should never execute unless we entered wrong data straight to the database
		{
			System.err.println("UserProfileServiceImpl.retrieveBookRequests(): User profile not found for userid: " + userprofileid);
		}
		
		// When a user offers a book it gets added to the "book" table as well as the "user_book_offers" table
		// So we look for all the books offered by this user in the "book" table
		List<Book> books = bookMapper.findByUserprofileid(userprofileid);	
		
		
		for (Book book: books) 
		{
			if(!book.getRequestingUsers().isEmpty())	// Add only the books that have requests on them
			{
				//System.err.println("Testing : bookid=" + book.getIdbook());
				BookFormData bookFormdata = new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors(),
						book.getDescription(), book.getRequestingUsers());
				
				bookRequests.add(bookFormdata);
			}
		}
		
		return bookRequests;
	}

	@Override
	public List<UserProfileFormData> retrieveRequestingUsers(int bookid) {
		
		List<UserProfileFormData> requestingUsers = new ArrayList<>();
		
		Book book = bookMapper.findByIdbook(bookid);
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
	        List<UserProfile> requestingUsers = bookMapper.findByIdbook(bookid).getRequestingUsers();
	        	
	        for (Iterator<UserProfile> iterator = requestingUsers.iterator(); iterator.hasNext();) {
	            UserProfile requestingUser = iterator.next();
	            if (requestingUser.getUserprofile_id() == userProfile.getUserprofile_id()) {
	                iterator.remove(); 
	                break; 
	            }
	        }
	        bookMapper.save(bookMapper.findByIdbook(bookid));
	    } else {
	        System.out.println("User profile not found for username: " + username);
	    }
		
	}

}
