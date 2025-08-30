package com.corelia.taskmanager.bean;

import com.corelia.taskmanager.model.Task;

import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.User;
import com.corelia.taskmanager.security.CustomUserDetails;
import com.corelia.taskmanager.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.inject.Named;


@Named
@SessionScope
public class TaskBean implements Serializable {

    private final TaskService taskService;
    private Task task;
    private List<Task> tasks;
    private List<Status> statusOptions = Arrays.asList(Status.values());

    // ممكن تضيف طريقة تجيب الـ logged user من session
    private User loggedUser;  

    public TaskBean(TaskService taskService) {
        this.taskService = taskService;
        this.tasks = taskService.getTasksForUser(loggedUser);
        this.task = new Task();
    }

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) auth.getPrincipal()).getUser();
        }
        return null;
    }

    public List<Task> getTasks() {
        User user = getLoggedUser(); 
        tasks = taskService.getTasksForUser(user);
        return tasks;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Status> getStatusOptions() {
        return statusOptions;
    }

    public String newTask() {
        task = new Task();
        return "task_form.xhtml?faces-redirect=true";
    }

    public String editTask(Task t) {
        task = t;
        return "task_form.xhtml?faces-redirect=true";
    }

    public String saveTask() {
        task.setOwner(getLoggedUser()); // ربط المهمة بالمستخدم اللي عامل login
        taskService.saveTask(task);
        return "tasks.xhtml?faces-redirect=true";
    }

    public String deleteTask(Task t) {
        taskService.deleteTask(t.getId());
        return "tasks.xhtml?faces-redirect=true";
    }
}
