package com.corelia.taskmanager.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.repository.TaskRepository;

@Service
public class ReportService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Map<String, Long> getTaskStatusCounts() {
        List<Task> tasks = getAllTasks();
        Map<String, Long> statusCounts = new HashMap<>();
        statusCounts.put("TODO", tasks.stream().filter(t -> t.getStatus() == Status.TODO).count());
        statusCounts.put("IN_PROGRESS", tasks.stream().filter(t -> t.getStatus() == Status.IN_PROGRESS).count());
        statusCounts.put("DONE", tasks.stream().filter(t -> t.getStatus() == Status.DONE).count());
        return statusCounts;
    }

    public Map<String, Long> getTasksPerUser() {
        List<Task> tasks = getAllTasks();
        return tasks.stream()
                .collect(Collectors.groupingBy(t -> t.getUser().getUsername(), Collectors.counting()));
    }
}

