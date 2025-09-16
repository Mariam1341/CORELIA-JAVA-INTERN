package com.corelia.taskmanager.repository;


import com.corelia.taskmanager.dto.TaskReportDTO;
import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserUsername(String username);

	List<Task> findByUserAndStatus(User user, Status status);
	

    @Query("SELECT t.status, COUNT(t) FROM Task t GROUP BY t.status")
    List<TaskReportDTO> countTasksByStatus();

    @Query("SELECT t.user.username, COUNT(t) FROM Task t GROUP BY t.user.username")
    List<Object[]> countTasksByUser();

	void deleteAllByUser(User user);
	
	List<Task> findAllByOrderByDeadlineAsc();

	List<Task> findAllByDeadlineAndStatus(Date deadline, Status status);


}

