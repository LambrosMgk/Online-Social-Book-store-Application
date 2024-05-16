package myy803.SocialBookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.SocialBookStore.entity.Book;
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
    public String getUserHome()
    {
    	 //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 //String currentPrincipalName = authentication.getName();
		 //System.err.println("UserController : username=" + currentPrincipalName);
		
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
    public String showRequests(Model model)
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
		
    	List<BookFormData> offers = userProfileService.retreiveBookOffers(username);	// Get the requests for books this user has from base
    	
    	model.addAttribute("offers", offers);
    	
        return "user/ShowRequests";
    }
    
    
    @RequestMapping("/user/show-recommendations")
    public String showRecommendations()
    {
		// Get profile from base and check what categories the user likes
    	// then sort the books by these categories
        return "user/";
    }
    
    
    @RequestMapping("/user/my-offerings")
    public String myOfferings()
    {
		// Get the offering list for this user from base
        return "user/";
    }
    
    
    @RequestMapping("/user/my-wishlist")
    public String myWishlist()
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
    	    
        return "user/searchBook";
    }
    
    
    @RequestMapping("/user/seeBook")
    public String seeBook(@RequestParam("idbook") int theBookId, Model theModel) 
    {
    	
    	Book book = bookService.findBookByid(theBookId);
    	BookFormData theBook= new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors(),book.getDescription()); 
    	theModel.addAttribute("book", theBook);
    	
    	return "user/BookDescription";
    }
}
