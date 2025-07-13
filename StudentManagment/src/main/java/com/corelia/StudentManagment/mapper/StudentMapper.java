package com.corelia.StudentManagment.mapper;

import com.corelia.StudentManagment.model.Student;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("emai"),
                            rs.getInt("age"));
                }
}
