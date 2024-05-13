package myy803.SocialBookStore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GuestController {

    @RequestMapping("/guest/dashboard")
    public String getUserHome()
    {
   	 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String currentPrincipalName = authentication.getName();
		 System.err.println("GuestController : " + currentPrincipalName);
		
        return "guest/dashboard";
    }
    
    @RequestMapping("/guest/create-profile")
    public String createProfile() 
    {
    	// model.addAttribute("userProfile", new UserProfile());  ???????
        return "guest/createProfile"; // assuming you have a createProfile.html in your templates
    }
    
    @RequestMapping("/guest/search-book")
    public String searchBook() 
    {
        return "guest/search-book";
    }
}
