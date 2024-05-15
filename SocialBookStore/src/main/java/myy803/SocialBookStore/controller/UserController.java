package myy803.SocialBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.SocialBookStore.formsData.UserProfileFormData;
import myy803.SocialBookStore.service.UserProfileService;

@Controller
public class UserController {
	
	@Autowired
	UserProfileService userProfileService;

    @RequestMapping("/user/dashboard")
    public String getUserHome()
    {
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String currentPrincipalName = authentication.getName();
		 System.err.println("UserController : " + currentPrincipalName);
		
        return "user/dashboard";
    }
    
    
    @RequestMapping("/user/edit-profile")
    public String editProfile(Model model)	// Get profile from base and autofill the fields in editProfile.html
    {
    	// User is already signed in so we can get the username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserProfileFormData userProfileForm = userProfileService.retreiveProfile(authentication.getName());
		
		model.addAttribute("userProfileForm", userProfileForm);

		return "user/editProfile";
    }
    
    
    @RequestMapping("/user/save-profile")
    public String saveProfile(Model model)
    {
    	
    	
    	
    	System.out.println("User profile updated successfully!");
    	model.addAttribute("successMessage", "Update successfull!");
    	
		return "user/dashboard";
    }
    
    
    @RequestMapping("/user/show-requests")
    public String showRequests()
    {
		// Get the requests for books this user has from base
        return "user/";
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
    public String searchBook()
    {
		// Search for the requested book? redirect to a page for this or dynamically show this to user dashboard
        return "user/";
    }
}
