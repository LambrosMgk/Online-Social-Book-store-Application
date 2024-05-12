package myy803.BookStore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.BookStore.entity.User;
import myy803.BookStore.service.UserService;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/")
    public String home(Authentication authentication) 
    {
        if (authentication != null && authentication.isAuthenticated()) 
        {
        	System.out.println("User is already logged in, redirect to the user dashboard");
            return "redirect:/user/userDashboard";
        }
        else
        {
        	System.out.println("User is not logged in, show the homepage");
            return "homepage";	
        }
    }
    
    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        
    	User user = userService.findByUsername(username);
    	String encoded_password;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        
        if(user == null)	// Couldn't find username in the table
        {
        	model.addAttribute("error", true); // Show error if login fails
            System.out.println("Login not Successful");    //debug
            return "redirect:/?loginError"; // Redirect back to the homepage with an error
        }
        
        encoded_password = user.getPassword();
        if (encoder.matches(password, encoded_password)) 
        {
            System.out.println("Login Successful");    //debug
            return "/user/userDashboard"; // Redirect to user dashboard
        } 
        else 
        {
            model.addAttribute("error", true); // Show error if login fails
            System.out.println("Login not Successful");    //debug
            return "redirect:/?loginError"; // Redirect back to the homepage with an error
        }
    }

    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration"; // Display the registration form
    }

    
    @PostMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model) 
    {
    	System.out.println("User : " + user);
    	if (userService.isUserPresent(user)) 	// Check if User already registered
    	{
          model.addAttribute("errorMessage", "User already registered!");
          return "redirect:/?registerError"; // Return to the homepage page with error message
    	}
    	
        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");
        return "redirect:/?registerSuccess"; // Redirect to the homepage with success message
    }
    
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
    {
        if (authentication != null) 
        {
            // Invalidates the user session
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            System.out.println("AuthController : Logout Successful!");
        }
        
        
        return "redirect:/";	// Redirect to the homepage or any other appropriate URL after logout
    }
    
    
    @ExceptionHandler(Exception.class)  // Catch all exceptions
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred.");  // Default message
        model.addAttribute("exception", ex.getMessage());  // Exception message
        model.addAttribute("errorDetails", ex.toString());  // Full exception details

        return "error";  // Return the error.html template
    }
}
