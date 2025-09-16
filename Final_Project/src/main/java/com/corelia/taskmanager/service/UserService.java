package com.corelia.taskmanager.service;

import com.corelia.taskmanager.model.Role;
import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.repository.TaskRepository;
import com.corelia.taskmanager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {

	@Autowired
    private  UserRepository userRepository;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private TaskRepository taskRepository;

	
	   @Autowired
	    private JavaMailSender mailSender;
    
	private final Pattern emailPattern = Pattern.compile(
	        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
	    );

	    public void addUser(String username, String email, String password) {
       if (!emailPattern.matcher(email).matches()) {
	            throw new RuntimeException("Invalid email format");
	        }
        if (userRepository.existsByUsername(username)) {
	            throw new RuntimeException("Username already exists");
	        }
        if (userRepository.existsByEmail(email)) {
	            throw new RuntimeException("Email already exists");
	        }

	        User user = new User();
	        user.setUsername(username);
	        user.setEmail(email);
	        user.setPassword(passwordEncoder.encode(password));
	        user.setRole(Role.USER);	    
	        userRepository.save(user);
	    }
	
	    
    

	 

	    public User getUserByUsername(String username) {
	        return userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }

	    public void updateEmail(String username, String newEmail) {
	        if (userRepository.existsByEmail(newEmail)) {
	            throw new RuntimeException("Email already in use");
	        }
	        User user = getUserByUsername(username);
	        user.setEmail(newEmail);
	        userRepository.save(user);
	    }

	    public void sendPasswordResetLink(String username) {
	        User user = getUserByUsername(username);

	        String token = UUID.randomUUID().toString();
	        user.setResetToken(token);
	        userRepository.save(user);

	        String resetLink = "http://localhost:8080/reset-password.xhtml?token=" + token;

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(user.getEmail());
	        message.setSubject("Reset Your Password");
	        message.setText("Click the link to reset your password: " + resetLink);

	        mailSender.send(message);
	    }
	

	  
	    public void resetPassword(String token, String newPassword) {
	        User user = userRepository.findByResetToken(token)
	                .orElseThrow(() -> new RuntimeException("Invalid or expired reset token"));

	        user.setPassword(passwordEncoder.encode(newPassword));
	        user.setResetToken(null); // invalidate token after use
	        userRepository.save(user);
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

   @Transactional
   public void deleteUser(String username) {
       User user = userRepository.findByUsername(username)
           .orElseThrow(() -> new RuntimeException("User not found"));
       taskRepository.deleteAllByUser(user);
       userRepository.delete(user);
   }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
