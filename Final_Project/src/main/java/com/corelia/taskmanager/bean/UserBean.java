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

    @PostConstruct
    public void init() {
        allUsers = userService.getAllUsers();
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

    public List<User> getAllUsers() {
        return allUsers;
    }
}
