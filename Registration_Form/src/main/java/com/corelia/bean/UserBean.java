package com.corelia.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String register() {
        // Simulate saving the data
        System.out.println("Registered: with name: " + name + ", email: " + email);
        return null;
    }
}
