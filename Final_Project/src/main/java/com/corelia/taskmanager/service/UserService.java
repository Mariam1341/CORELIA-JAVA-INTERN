package com.corelia.taskmanager.service;

import com.corelia.taskmanager.model.Role;
import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
    private  UserRepository userRepository;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
    
	
    
	public User addUser(String username,String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

      
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public void updateUser(User user) {
	        userRepository.save(user);
   }public List<User> getAllUsers() {
       return userRepository.findAll();
   }

   public void updateRole(String username, String newRole) {
       User user = userRepository.findByUsername(username)
           .orElseThrow(() -> new RuntimeException("User not found"));
       if(newRole.equals("ADMIN")) {
    	   user.setRole(Role.ADMIN);
       }else {
    	   user.setRole(Role.USER);
       }
    
       userRepository.save(user);
   }

   public void deleteUser(String username) {
       User user = userRepository.findByUsername(username)
           .orElseThrow(() -> new RuntimeException("User not found"));
       userRepository.delete(user);
   }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
