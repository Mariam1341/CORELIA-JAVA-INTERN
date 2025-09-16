package com.corelia.taskmanager.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.repository.TaskRepository;



@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(cron = "0 0 0 * * ?") 
    public void sendDeadlineReminders() {
    	 try {
    	        LocalDate tomorrowLocal = LocalDate.now().plusDays(1);
    	        Date tomorrow = java.sql.Date.valueOf(tomorrowLocal);
    	        System.out.println("Tomorrow in code: " + tomorrow);

    	        List<Task> tasks = taskRepository.findAllByDeadlineAndStatus(tomorrow, Status.TODO);
    	        System.out.println("Found tasks: " + tasks.size());

    	        for (Task task : tasks) {
    	            System.out.println("Sending to: " + task.getUser().getEmail());
    	            sendEmail(task.getUser().getEmail(),
    	                    "Task Reminder: " + task.getTitle(),
    	                    "Reminder: Your task '" + task.getTitle() + "' is due tomorrow (" + task.getDeadline() + ")");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace(); 
    	        }
    }


    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
