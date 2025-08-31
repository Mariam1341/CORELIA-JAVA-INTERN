package com.corelia.taskmanager.repository;


import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserUsername(String username);

	List<Task> findByUserAndStatus(User user, Status status);
}

