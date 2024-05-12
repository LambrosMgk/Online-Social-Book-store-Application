package myy803.BookStore.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomSecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws java.io.IOException 
	{
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted())
            return;
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
	

    protected String determineTargetUrl(Authentication authentication)
    {
    	String url = "/?loginError";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        
        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }
        
        if(roles.contains("USER") || roles.contains("GUEST"))
        {
        	System.out.println("User will be redirected to " + url);
        	url = "/user/userDashboard";
        }
        
        return url;
    }
}
