package com.corelia.taskmanager.bean;

import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named
@SessionScoped
public class ResetPasswordBean implements Serializable {

    private String token;
    private String newPassword;
    private String confirmPassword;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        // Read token from request parameters (works on initial GET)
        Map<String, String> params = FacesContext.getCurrentInstance()
                                                .getExternalContext()
                                                .getRequestParameterMap();
        this.token = params.get("token");
        // if token is not provided, we won't immediately fail — page will show message on submit
    }

    public String updatePassword() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (token == null || token.trim().isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid token", "Reset token is missing or invalid."));
            return null;
        }

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Passwords do not match."));
            return null;
        }

        try {
            userService.resetPassword(token, newPassword);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Password updated successfully."));
            // redirect to login page after success
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                fc.responseComplete();
                return null;
            } catch (IOException e) {
                // fallback navigation
                return "login.xhtml?faces-redirect=true";
            }
        } catch (RuntimeException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    // ===== Getters & Setters =====
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; } // important for viewParam & hidden input

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}