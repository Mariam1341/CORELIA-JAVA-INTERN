package com.corelia.taskmanager.bean;

import com.corelia.taskmanager.model.Task;

import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.security.CustomUserDetails;
import com.corelia.taskmanager.service.NotificationService;
import com.corelia.taskmanager.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class TaskBean implements Serializable {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private NotificationService notificationService;
    

    @Inject
    private AuthenticationBean authBean;
    
    
    
    
    
    private List<Task> todoTasks;
    private List<Task> inProgressTasks;
    private List<Task> doneTasks;
    
    private List<Task> tasks;
    private Task newTask = new Task();
   
    private Task selectedTask; 

    @PostConstruct
    public void init() {
        loadTasks();
    }



    // ✅ تنسيق التاريخ للعرض
    public String formatDeadline(Task task) {
        if (task.getDeadline() == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(task.getDeadline());
    }
    // ✅ هل متأخر؟
    public boolean isOverdue(Task task) {
        if (task.getDeadline() == null) return false;
        LocalDate today = LocalDate.now();
        LocalDate deadlineLocal = task.getDeadline().toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate();
        return deadlineLocal.isBefore(today);
    }

    public boolean isDueTomorrow(Task task) {
        if (task.getDeadline() == null) return false;
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate deadlineLocal = task.getDeadline().toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate();
        return deadlineLocal.isEqual(tomorrow);
    }


	public void sendDeadlineNotifications() {
		notificationService.sendDeadlineReminders();
	}

    public void loadTasks() {
        tasks = taskService.getTasksForUser(authBean.getUsername());
    }

    public void addTask() {
        taskService.addTask(newTask, authBean.getUsername());
        newTask = new Task();
        loadTasks();
    }

    public void advanceStatus(Task task) {
        taskService.advanceStatus(task);
        loadTasks();
    }
    
    // Delete
    public void deleteTask(Task task) {
    	taskService.deleteTask(task);
        tasks.remove(task);
    }

    // Edit
    public void editTask(Task task) {
    	taskService.updateTask(task);
    }


    public Task getSelectedTask() {
		return selectedTask;
	}



	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}



	public void setTodoTasks(List<Task> todoTasks) {
		this.todoTasks = todoTasks;
	}



	public void setInProgressTasks(List<Task> inProgressTasks) {
		this.inProgressTasks = inProgressTasks;
	}




	public void setDoneTasks(List<Task> doneTasks) {
		this.doneTasks = doneTasks;
	}
    public void prepareEdit(Task task) {
        this.selectedTask = task;
        this.newTask = task; 
        }
    
    public void addOrUpdateTask() {
        if (selectedTask != null) {
            taskService.updateTask(selectedTask);
        } else {
            taskService.addTask(newTask, authBean.getUsername());
            tasks.add(newTask);
        }
        resetForm();
    }
    private void resetForm() {
        newTask = new Task();
        selectedTask = null;
    }

	public List<Task> getTodoTasks() {
        return tasks.stream().filter(t -> t.getStatus() == Status.TODO).collect(Collectors.toList());
    }
    public List<Task> getInProgressTasks() {
    	  return tasks.stream().filter(t -> t.getStatus() == Status.IN_PROGRESS).collect(Collectors.toList());
    }
    
    public List<Task> getDoneTasks() {
  	  return tasks.stream().filter(t -> t.getStatus() == Status.DONE).collect(Collectors.toList());
  }

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public AuthenticationBean getAuthBean() {
		return authBean;
	}

	public void setAuthBean(AuthenticationBean authBean) {
		this.authBean = authBean;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task getNewTask() {
		return newTask;
	}

	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}

    
}
