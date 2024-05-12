package myy803.SocialBookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestController {

    @RequestMapping("/guest/dashboard")
    public String getUserHome(){
//    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		 String currentPrincipalName = authentication.getName();
//		 System.err.println(currentPrincipalName);
		
        return "guest/dashboard";
    }
}
