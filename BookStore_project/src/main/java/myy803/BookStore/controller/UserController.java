package myy803.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import myy803.BookStore.entity.User;
import myy803.BookStore.service.UserService;

@Controller
public class UserController {
	@Autowired
    UserService userService;
    
    @GetMapping("/user/userDashboard")
    public String userDashboard(Model model) 
    {
//   	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		 String currentPrincipalName = authentication.getName();
//		 System.err.println(currentPrincipalName);
    	
        //model.addAttribute("username", username);
        System.out.println("UserController : redirecting to user dashboard");	//debug
        
        return "user/userDashboard";
    }

    
    @GetMapping("/user/edit-profile")
    public String edit_profile(Model model)
    {
    	User user = new User();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	user = userService.findByUsername(authentication.getName());	//this should not be null because to get here you have to be logged in
    	model.addAttribute("user", user);
    	
    	return "user/UserProfile";
    }
}
