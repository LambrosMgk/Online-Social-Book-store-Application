package myy803.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            return "user/userDashboard"; // Redirect to user dashboard
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

    
    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
    	if (userService.isUserPresent(user)) {
          model.addAttribute("errorMessage", "User already registered!");
          return "/homepage"; // Return to the homepage page with error message
    	}
        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");
        return "redirect:/?registerSuccess"; // Redirect to the homepage with success message
    }
    
    
    @ExceptionHandler(Exception.class)  // Catch all exceptions
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred.");  // Default message
        model.addAttribute("exception", ex.getMessage());  // Exception message
        model.addAttribute("errorDetails", ex.toString());  // Full exception details

        return "error";  // Return the error.html template
    }
}
