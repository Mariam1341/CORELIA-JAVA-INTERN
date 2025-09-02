package com.corelia.taskmanager.service;


import com.corelia.taskmanager.dto.TaskReportDTO;
import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired 
    private UserService userService;

    public List<Task> getTasksForUser(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public void addTask(Task task, String username) {
        // نفترض UserService موجودة
    	User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        task.setUser(user);
        taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void advanceStatus(Task task) {
        switch (task.getStatus()) {
            case TODO:
                task.setStatus(Status.IN_PROGRESS);
                break;
            case IN_PROGRESS:
                task.setStatus(Status.DONE);
                break;
            case DONE:
                task.setStatus(Status.TODO);
                break;
        }
        taskRepository.save(task);
    }

	public List<TaskReportDTO> getTaskCountByStatus() {
		
		return taskRepository.countTasksByStatus();
	}

}
