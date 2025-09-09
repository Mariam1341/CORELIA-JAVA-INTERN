package com.corelia.taskmanager.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.service.ReportService;

@Named
@SessionScoped
public class ReportBean implements Serializable {

    private PieChartModel taskStatusModel;
    private BarChartModel taskByUserModel;
    private List<Task> allTasks;

    @Autowired
    private ReportService reportService;

    @PostConstruct
    public void init() {
        allTasks = reportService.getAllTasks();

        // ===== Pie Chart: Tasks by Status =====
        Map<String, Long> statusCounts = reportService.getTaskStatusCounts();
        taskStatusModel = new PieChartModel();
        statusCounts.forEach(taskStatusModel::set);
        taskStatusModel.setSeriesColors("6c757d,ffc107,28a745");

        // ===== Bar Chart: Tasks by User =====
        Map<String, Long> tasksPerUser = reportService.getTasksPerUser();
        ChartSeries series = new ChartSeries();
        series.setLabel("Tasks per User");
        tasksPerUser.forEach(series::set);

        taskByUserModel = new BarChartModel();
        taskByUserModel.addSeries(series);
    }

    public PieChartModel getTaskStatusModel() { return taskStatusModel; }
    public BarChartModel getTaskByUserModel() { return taskByUserModel; }
    public List<Task> getAllTasks() { return allTasks; }
}
