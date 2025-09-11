package com.corelia.taskmanager.bean;

import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.service.UserService;

@Named
@SessionScope
public class UserBean {

	@Autowired
    private UserService userService;

    private List<User> allUsers;
    private String username;
    private String email;

    @PostConstruct
    public void init() {
    	allUsers = userService.getAllUsers();
        loadUserData();
    }

    private void loadUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            User user = userService.getUserByUsername(auth.getName());
            if (user != null) {
                this.username = user.getUsername();
                this.email = user.getEmail();
            }
        }
    }

    // ===== Update Email =====
    public void updateEmail() {
        try {
            userService.updateEmail(username, email);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Email updated successfully."));
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    // ===== Send Reset Password Link =====
    public void sendPasswordResetLink() {
        try {
            userService.sendPasswordResetLink(username);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                            "Password reset link sent to your email."));
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

   
    public void makeAdmin(String username) {
        userService.updateRole(username, "ADMIN");
        allUsers = userService.getAllUsers(); // refresh table
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
                             "Success", username + " is now an Admin."));
    }

    public void deleteUser(String username) {
        userService.deleteUser(username);
        allUsers = userService.getAllUsers(); // refresh table
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
                             "Deleted", "User " + username + " has been removed."));
    }

    // ===== Getters & Setters =====
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
public List<User> getAllUsers() {return allUsers;}
}
