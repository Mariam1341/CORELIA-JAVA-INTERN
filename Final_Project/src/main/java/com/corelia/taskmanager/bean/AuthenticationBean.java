package com.corelia.taskmanager.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;

import com.corelia.taskmanager.service.UserService;


@Named
@SessionScope
public class AuthenticationBean {

		private String username;

		
	    private String password;
	    
	    private String email;


	    @Autowired
	    private UserService userService;
	    
	    
	    public String getUsername() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	            return auth.getName(); 
	        }
	        return "";
	    }

	    public boolean getIsAdmin() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        return auth.getAuthorities().stream()
	                   .anyMatch(r -> r.getAuthority().equals("ADMIN"));
	    }

   public void register() {
       try {
           userService.addUser(username, email, password);
           FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                "Success", "Registration successful! Please login."));
           FacesContext.getCurrentInstance().getExternalContext()
                       .redirect("login.xhtml");
       } catch (RuntimeException e) {
           FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error", e.getMessage()));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   public String logout() {
       SecurityContextHolder.clearContext();
       FacesContext.getCurrentInstance().getExternalContext()
           .invalidateSession();
       return "login.xhtml?faces-redirect=true";
   }
   public void setUsername(String username) { this.username = username; }
   public String getPassword() { return password; }
   public void setPassword(String password) { this.password = password; }
}
