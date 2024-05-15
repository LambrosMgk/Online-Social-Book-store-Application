package myy803.SocialBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.SocialBookStore.formsData.UserProfileFormData;
import myy803.SocialBookStore.service.UserService;


@Controller
public class GuestController {
	
	@Autowired
	UserService userService;

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
    	userProfileFormData.setFavoriteCategories(categoryService.getAllCategories());
    	userProfileFormData.setFavoriteAuthors();
    	
    	model.addAttribute("userProfileFormData", userProfileFormData);
        return "guest/createProfile";
    }
    
    
    @RequestMapping("/guest/save-profile")
    public String saveProfile(@ModelAttribute("userProfileFormData") UserProfileFormData userProfileForm,Model model) 
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	//save profile to base
    	
    	System.out.println("Fullname : " + userProfileForm.getFullname() + ", Categories : " + userProfileForm.getFavoriteCategories());	// debug
    	model.addAttribute("successMessage", "Profile created, login again to have access as user!");
    	return "guest/dashboard"; // assuming you have a createProfile.html in your templates
    }
    
    
    @RequestMapping("/guest/search-book")
    public String searchBook() 
    {
        return "guest/search-book";
    }
}
