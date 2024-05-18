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
		//System.err.println("UserController : userProfileid=" + userProfileForm.getUserprofile_id());
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
    	
        return "user/showRequests";
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
    		return "user/seeRequests";
    	}
    	
    	model.addAttribute("bookTitle", requestedBook.getTitle());
    	model.addAttribute("users", requestedBook.getRequestingUsers());
    	
        return "user/seeRequests";
    }
    
    
    
    
    @RequestMapping("/user/show-recommendations")
    public String showRecommendations(@RequestParam("userprofile_id") int userid)
    {
		// Get profile from base and check what categories the user likes
    	// then sort the books by these categories
        return "user/showRecommendations";
    }
    
    
    
    
    @RequestMapping("/user/my-offers-list")
    public String myBookOfferList(@RequestParam("userprofile_id") int userprofile_id, Model theModel)
    {
		List<BookFormData> bookOffers = userProfileService.retreiveBookOffers(userprofile_id);
		
		theModel.addAttribute("books", bookOffers); 
        theModel.addAttribute("userprofile_id", userprofile_id);
        
		return "/user/myOffersList";
    }
    @RequestMapping("/user/myOffersBookDetails")
    public String myOfferingsDetails(@RequestParam("idbook") int theBookId, @RequestParam("userprofile_id") int userprofile_id, Model theModel) 
    {
    	
    	Book book = bookService.findBookByid(theBookId);
    	BookFormData theBook= new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors(),book.getDescription(),book.getRequestingUsers()); 
    	theModel.addAttribute("userprofile_id", userprofile_id);
    	theModel.addAttribute("book", theBook);
    	theModel.addAttribute("bookAuthors", theBook.getBookAuthors());
    	
    	return "user/myOffersBookDetails";
    }
    
    
    @RequestMapping("/user/addBookOffer")
    public String addBookOffer(@RequestParam("userprofile_id") int userprofile_id, Model theModel)
    {
    	List<BookCategory> categories = bookCategoryService.ReturnCategories();
    	List<BookAuthor> authors = bookAuthorService.ReturnAuthors();
    	BookFormData book = new BookFormData();
    	
    	book.setBookAuthors(authors);
    	theModel.addAttribute("newBook",book);
        theModel.addAttribute("userprofile_id", userprofile_id); 
        theModel.addAttribute("bookCategories",categories);
        //theModel.addAttribute("bookAuthors",authors);
        
		return "/user/addBookOffer";
    }
    @RequestMapping("/user/BookSave")
    public String saveBookOffer(@RequestParam("userprofile_id") int userid,@ModelAttribute("newBook") BookFormData newBook,Model theModel)
    {
    	
    	bookService.saveBook(newBook,userid);
    	
    	return"redirect:/user/dashboard";
    }
    @RequestMapping("/user/authorCreate")
    public String newAuthorCreate(@RequestParam("userprofile_id") int userid, Model theModel)
    {
    	BookAuthor bookauthor = new BookAuthor();
    	
    	theModel.addAttribute("newBookAuthor",bookauthor);
    	
    	return"/user/createAuthor";
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
    	BookFormData theBook= new BookFormData(book.getIdbook(),book.getTitle(),book.getBookCategory(),book.getBookAuthors(),book.getDescription(),book.getRequestingUsers()); 
    	theModel.addAttribute("book", theBook);
    	theModel.addAttribute("bookAuthors", theBook.getBookAuthors());
    	
    	return "user/searchBookDescription";
    }

}
