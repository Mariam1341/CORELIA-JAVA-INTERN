package com.corelia.employee.service;

import com.corelia.employee.dao.EmployeeDAO;
import com.corelia.employee.entity.Employee;
import java.util.List;


public class EmployeeService {
	  private final EmployeeDAO dao = new EmployeeDAO();

	    public List<Employee> getAllEmployees() {
	        return dao.findAll();
	    }

	    public void save(Employee e) {
	        dao.save(e);
	    }

	    public void delete(Long id) {
	        dao.delete(id);
	    }
	

}

