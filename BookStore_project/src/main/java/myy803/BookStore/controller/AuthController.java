package myy803.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.BookStore.entity.User;
import myy803.BookStore.service.UserService;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        
    	String encoded_password = userService.findByUsername(username).getPassword();
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	
    	if (encoder.matches(password, encoded_password)) {
            return "redirect:/user/dashboard"; // Redirect to some authenticated area, assuming 'dashboard' is a valid URL
        } else {
            model.addAttribute("error", true); // Show error if login fails
            return "/homepage"; // Redirect back to the homepage with an error
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration"; // Display the registration form
    }

    @PostMapping("/save")
    public String registerUser(
    	@RequestParam("username") String username,
    	@RequestParam("password") String password,
        @RequestParam("fullname") String fullname,
        @RequestParam("address") String address,
        @RequestParam("age") Integer age,
        @RequestParam("phone") String phone,
        @RequestParam(value = "category", required = false) String[] categories,
        @RequestParam(value = "author", required = false) String[] authors,
        Model model
    ) {
    	
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("user_role");
        //user.setFullname(fullname);
        //user.setAddress(address);
        //user.setAge(age);
        //user.setPhone(phone);
        //user.setCategories(categories); // This assumes a setter for categories
        System.out.println(username + ", " + password + ", categories : ");	//test1
        
        // Check if the user is already present
        if (userService.isUserPresent(user)) {
            model.addAttribute("errorMessage", "User already registered!");
            return "/homepage"; // Return to the homepage page with error message
        }
        
        //test2
        for(String x : categories)
        	System.out.println(x);
        
        
        userService.saveUser(user); // Save the newly created user
        model.addAttribute("successMessage", "User registered successfully!");
        return "redirect:/login"; // Redirect to the login page after registration
    }
    
    @ExceptionHandler(Exception.class)  // Catch all exceptions
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred.");  // Default message
        model.addAttribute("exception", ex.getMessage());  // Exception message
        model.addAttribute("errorDetails", ex.toString());  // Full exception details

        return "error";  // Return the error.html template
    }
}
