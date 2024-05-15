package myy803.SocialBookStore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.SocialBookStore.formsData.UserProfileFormData;
import myy803.SocialBookStore.service.BookAuthorService;
import myy803.SocialBookStore.service.BookCategoryService;
import myy803.SocialBookStore.service.UserProfileService;
import myy803.SocialBookStore.service.UserService;


@Controller
public class GuestController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	BookCategoryService bookCategoryService;
	@Autowired
	BookAuthorService bookAuthorService;

    @RequestMapping("/guest/dashboard")
    public String getUserHome()
    {
   	 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String currentPrincipalName = authentication.getName();
		 System.err.println("GuestController : username = " + currentPrincipalName);
		
        return "guest/dashboard";
    }
    
    
    @RequestMapping("/guest/create-profile")
    public String createProfile(Model model)
    {
    	UserProfileFormData userProfileFormData = new UserProfileFormData();
    	
    	// Give all the categories as favorite so they appear in the html form
    	userProfileFormData.setFavoriteCategories(bookCategoryService.ReturnCategories());
    	userProfileFormData.setFavoriteAuthors(bookAuthorService.ReturnAuthors());
    	
    	model.addAttribute("userProfileFormData", userProfileFormData);
    	
        return "guest/createProfile";
    }
    
    
    @RequestMapping("/guest/save-profile")
    public String saveProfile(@ModelAttribute("userProfileFormData") UserProfileFormData userProfileForm,Model model) 
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	
    	//save profile to base
    	userProfileForm.setUsername(username);
    	userProfileForm.setUser_id(userService.findByUsername(username).getUserid());	// This should not throw an error since the user is logged in
    	userProfileService.save(userProfileForm);
    	
    	
    	model.addAttribute("successMessage", "Profile created, you are now a user!");
    	return "redirect:/logout";
    }
    
    
    @RequestMapping("/guest/search-book")
    public String searchBook() 
    {
        return "guest/search-book";
    }
}
