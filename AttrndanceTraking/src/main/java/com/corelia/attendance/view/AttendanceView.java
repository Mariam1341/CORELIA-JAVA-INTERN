package com.corelia.attendance.view;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.corelia.attendance.model.AttendanceRecord;
import com.corelia.attendance.repository.AttendanceRepository;
import com.corelia.attendance.service.AttendanceService;

@Named
@ViewScoped

public class AttendanceView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AttendanceService attendanceService;
    @Inject
    private AttendanceRepository repository;


    private List<AttendanceRecord> records;
    private AttendanceRecord selectedRecord = new AttendanceRecord();

    @PostConstruct
    public void init() {
        records = attendanceService.getAllRecords();
    }

    public void saveRecord() {
        if (selectedRecord.getId() == null) {
            attendanceService.addRecord(selectedRecord);
        } else {
            attendanceService.update(selectedRecord.getId(), selectedRecord);
        }
        records = attendanceService.getAllRecords();
        selectedRecord = new AttendanceRecord();
        clearForm(); 
    }

    public void deleteRecord(Long id) {
        attendanceService.delete(id);
        records = attendanceService.getAllRecords();
    }

    public void clearForm() {
        selectedRecord = new AttendanceRecord();
    }

    // Getters and Setters
    public List<AttendanceRecord> getRecords() { return records; }
    public AttendanceRecord getSelectedRecord() { return selectedRecord; }
    public void setSelectedRecord(AttendanceRecord selectedRecord) { this.selectedRecord = selectedRecord; }
}
