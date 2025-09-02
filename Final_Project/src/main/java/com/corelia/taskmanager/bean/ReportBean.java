package com.corelia.taskmanager.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.repository.TaskRepository;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class ReportBean implements Serializable {

	  private PieChartModel taskStatusModel;
	    private BarChartModel taskByUserModel;
	    private List<Task> allTasks;

	    @Autowired
	    private TaskRepository taskRepository; // استخدمي repository الحقيقي

	    @PostConstruct
	    public void init() {
	        // جلب كل المهام من قاعدة البيانات
	        allTasks = taskRepository.findAll();

	        // ===== Pie Chart: Tasks by Status =====
	        long todo = allTasks.stream().filter(t -> t.getStatus() == Status.TODO).count();
	        long inProgress = allTasks.stream().filter(t -> t.getStatus() == Status.IN_PROGRESS).count();
	        long done = allTasks.stream().filter(t -> t.getStatus() == Status.DONE).count();

	        taskStatusModel = new PieChartModel();
	        taskStatusModel.set("TODO", todo);
	        taskStatusModel.set("IN_PROGRESS", inProgress);
	        taskStatusModel.set("DONE", done);
	        
	        taskStatusModel.setSeriesColors("6c757d,ffc107,28a745");


	        // ===== Bar Chart: Tasks by User =====
	        taskByUserModel = new BarChartModel();
	        ChartSeries series = new ChartSeries();
	        series.setLabel("Tasks per User");

	        List<String> labels = allTasks.stream()
	                                      .map(t -> t.getUser().getUsername())
	                                      .distinct()
	                                      .collect(Collectors.toList());

	        for(String username : labels) {
	            long count = allTasks.stream().filter(t -> t.getUser().getUsername().equals(username)).count();
	            series.set(username, count);
	        }

	        taskByUserModel.addSeries(series);
	    }

	    // ===== Getters =====
	    public PieChartModel getTaskStatusModel() { return taskStatusModel; }
	    public BarChartModel getTaskByUserModel() { return taskByUserModel; }
	    public List<Task> getAllTasks() { return allTasks; }

	}