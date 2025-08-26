package com.corelia.attendance.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corelia.attendance.model.AttendanceRecord;
import com.corelia.attendance.repository.AttendanceRepository;


@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<AttendanceRecord> getAllRecords() {
        return attendanceRepository.findAll();
    }

    public AttendanceRecord addRecord(AttendanceRecord record) {
        return attendanceRepository.save(record);
    }

    public AttendanceRecord update(Long id, AttendanceRecord updated) {
        AttendanceRecord existing = attendanceRepository.findOne(id);
        existing.setEmployeeName(updated.getEmployeeName());
        existing.setDate(updated.getDate());
        existing.setPresent(updated.isPresent());
        return attendanceRepository.save(existing);
    }

    public void delete(Long id) {
        attendanceRepository.delete(id);
    }

}
