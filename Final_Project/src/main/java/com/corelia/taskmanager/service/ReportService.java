package com.corelia.taskmanager.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corelia.taskmanager.model.Status;
import com.corelia.taskmanager.model.Task;
import com.corelia.taskmanager.repository.TaskRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


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
   


    public byte[] exportTasksToCsv(List<Task> tasks) throws Exception {
        StringBuilder sb = new StringBuilder();

        // Header
        sb.append("Task Name,Assigned To,Status\n");

        // Data
        for (Task t : tasks) {
            String title = t.getTitle() != null ? t.getTitle() : "";
            String username = (t.getUser() != null && t.getUser().getUsername() != null) ? t.getUser().getUsername() : "";
            String status = t.getStatus() != null ? t.getStatus().name() : "";

            sb.append(title).append(",")
              .append(username).append(",")
              .append(status).append("\n");
        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
    
    public byte[] exportTasksToPdf(List<Task> tasks) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph title = new Paragraph("Tasks Report",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20); 
            document.add(title);

               PdfPTable table = new PdfPTable(3); 
            table.setWidthPercentage(100); 
            table.setSpacingBefore(10f);

            table.addCell("Task Name");
            table.addCell("User Name");
            table.addCell("Status");

            for (Task task : tasks) {
                table.addCell(task.getTitle());
                table.addCell(task.getUser().getUsername());
                table.addCell(task.getStatus().name());
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Error while generating PDF", e);
        }

        return out.toByteArray();
    }

    

}

