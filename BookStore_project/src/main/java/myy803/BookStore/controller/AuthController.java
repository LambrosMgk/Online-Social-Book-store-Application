package myy803.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.BookStore.entity.User;
import myy803.BookStore.service.UserService;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String homepage() {
        return "homepage"; 		// Refers to a Thymeleaf or JSP file called "homepage"
    }
    
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Example login check (should use proper security/authentication mechanisms)
        if ("user".equals(username) && "pass".equals(password)) { // Simple example credentials
            //return "redirect:/dashboard"; // Redirect to some authenticated area
        } else {
            model.addAttribute("error", true); // Show error if login fails
            return "homepage"; // Redirect back to the homepage with an error
        }
        
        return "homepage";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model){
       
        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }
        
        System.out.println("User registered with username : " + user.getUsername());

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/signin";
    }
}
