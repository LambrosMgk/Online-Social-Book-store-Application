package myy803.SocialBookStore.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.SocialBookStore.entity.Book;
import myy803.SocialBookStore.entity.BookAuthor;
import myy803.SocialBookStore.entity.BookCategory;
import myy803.SocialBookStore.formsData.BookFormData;
import myy803.SocialBookStore.formsData.UserProfileFormData;
import myy803.SocialBookStore.service.BookAuthorService;
import myy803.SocialBookStore.service.BookCategoryService;
import myy803.SocialBookStore.service.BookService;
import myy803.SocialBookStore.service.UserProfileService;

@Controller
public class UserController {
	
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	BookCategoryService bookCategoryService;
	@Autowired
	BookAuthorService bookAuthorService;
	@Autowired
	BookService bookService;

    @RequestMapping("/user/dashboard")
    public String getUserHome(Model model)
    {
    	 //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 //String currentPrincipalName = authentication.getName();
		 //System.err.println("UserController : username=" + currentPrincipalName);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserProfileFormData userProfileForm = userProfileService.retreiveProfile(authentication.getName());
		
		model.addAttribute("user", userProfileForm);
		System.err.println("UserController : userProfileid=" + userProfileForm.getUserprofile_id());
		return "user/dashboard";
    }
    
    
    @RequestMapping("/user/edit-profile")
    public String editProfile(Model model)	// Get profile from base and autofill the fields in editProfile.html
    {
    	// User is already signed in so we can get the username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserProfileFormData userProfileForm = userProfileService.retreiveProfile(authentication.getName());
		
		userProfileForm.setFavoriteCategories(bookCategoryService.ReturnCategories());
		userProfileForm.setFavoriteAuthors(bookAuthorService.ReturnAuthors());
    	
		model.addAttribute("userProfileForm", userProfileForm);

		return "user/editProfile";
    }
    
    
    @RequestMapping("/user/save-profile")
    public String saveProfile(Model model)
    {
    	
    	//fill this later
    	
    	System.out.println("User profile updated successfully!");
    	model.addAttribute("successMessage", "Update successfull!");
    	
		return "user/dashboard";
    }
    
    
    @RequestMapping("/user/show-requests")
    public String showRequests(@RequestParam("userprofile_id") int userprofileid,Model model)
    {
    	// Get the requests for this user's books from base
    	List<BookFormData> requests = userProfileService.retrieveBookRequests(userprofileid);	
    	
    	model.addAttribute("requests", requests);
    	model.addAttribute("userprofile_id", userprofileid);
    	
        return "user/ShowRequests";
    }
    
    
    @RequestMapping("/user/see-requests")
    public String seeRequests(@RequestParam("bookid") int bookid,@RequestParam("userprofile_id") int userprofileid,Model model)
    {
    	BookFormData requestedBook = bookService.findBookFormDataByid(bookid);
    	
    	
    	model.addAttribute("userprofile_id", userprofileid);	// To help redirect the user to "user/ShowRequests?userprofile_id=X"
    	if(requestedBook == null)
    	{
    		model.addAttribute("successMessage", "?");
    		System.err.println("UserController : seeRequests failed");
    		return "user/SeeRequests";
    	}
    	
    	model.addAttribute("users", requestedBook.getRequestingUsers());
    	
        return "user/SeeRequests";
    }
    
    
    @RequestMapping("/user/show-recommendations")
    public String showRecommendations(@RequestParam("userprofile_id") int userid)
    {
		// Get profile from base and check what categories the user likes
    	// then sort the books by these categories
        return "user/";
    }
    
    
    @RequestMapping("/user/my-offerings")
    public String myOfferings(@RequestParam("userprofile_id") int userid, Model theModel)
    {
    	UserProfileFormData userForm = userProfileService.retreiveProfile(userid) ;  
		List<BookFormData> bookOffers = userProfileService.retreiveBookOffers(userForm.getUsername());
		
		theModel.addAttribute("books",bookOffers);// list of book offers in the html 
        theModel.addAttribute("user",userForm); // user
		return "/user/MyOffers";
    }
    
    
    @RequestMapping("/user/addBookOffer")
    public String addBookOffer(@RequestParam("userprofile_id") int userid, Model theModel)
    {
    	UserProfileFormData userForm = userProfileService.retreiveProfile(userid) ;  
    	List<BookCategory> categories = bookCategoryService.ReturnCategories();
    	BookAuthor bookauthor = new BookAuthor();
//    	userProfileService.retreiveProfile(0)
        BookFormData book = new BookFormData();
        theModel.addAttribute("book",book);
        theModel.addAttribute("user",userForm); 
        theModel.addAttribute("bookCategories",categories);
        theModel.addAttribute("bookauthor",bookauthor);
		return "/user/addOffer";
    }
    
    
    @RequestMapping("/user/BookSave")
    public String saveBookOffer(@RequestParam("userprofile_id") int userid,@ModelAttribute("book") BookFormData theBookFormData,Model theModel)
    {
    	String authors = theBookFormData.getNameOfTheAuthors();
        String[] ArrayOfAuthors = authors.split(",");
        List<String> ListOfAuthors = Arrays.asList(ArrayOfAuthors);
        
        
        for (String authorName : ListOfAuthors){ // Save the bookAuthor
        	BookAuthor bookAuthor = new BookAuthor();
        	bookAuthor.setName(authorName);
        	bookAuthorService.BookAuthorSave(bookAuthor);
        }
        
        bookService.saveBook(theBookFormData,userid);
        
    	
    	return"redirect:/user/dashboard";
    }
    
    
    @RequestMapping("/user/my-wishlist") //? to 8eloume
    public String myWishlist(@RequestParam("userprofile_id") int userid)
    {
		// Get the wish list for this user from base
        return "user/";
    }
    
    
    @RequestMapping("/user/search-book")
    public String searchBook(Model theModel) 
    {
    	// Search for the requested book or see all available books? redirect to a page for this or dynamically show this to user dashboard
        List<BookFormData> allTheBooks = bookService.findAllBooks();   	
    	theModel.addAttribute("books",allTheBooks);
    	    
        return "/user/searchBook";
    }
    
    
    @RequestMapping("/user/seeBook")
    public String seeBook(@RequestParam("idbook") int theBookId, Model theModel) 
    {
    	
    	Book book = bookService.findBookByid(theBookId);
    	BookFormData theBook= new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors(),
    			book.getDescription(),book.getRequestingUsers()); 
    	theModel.addAttribute("book", theBook);
    	
    	return "user/BookDescription";
    }
}
