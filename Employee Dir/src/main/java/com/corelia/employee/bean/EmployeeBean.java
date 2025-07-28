package com.corelia.employee.bean;

import com.corelia.employee.entity.Employee;
import com.corelia.employee.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name="employeeBean")
@ViewScoped
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private EmployeeService service;
    private List<Employee> employees;
    private Employee selectedEmployee;

    @PostConstruct
    public void init() {
        service = new EmployeeService();
        employees = service.getAllEmployees();
        selectedEmployee = new Employee();
    }

    public void save() {
        service.save(selectedEmployee);
        employees = service.getAllEmployees();
        selectedEmployee = new Employee();
    }

    public void edit(Employee emp) {
        selectedEmployee = emp;
    }

    public void delete(Long id) {
        service.delete(id);
        employees = service.getAllEmployees();
    }

    // --- getters & setters ---
    public List<Employee> getEmployees() { return employees; }
    public Employee getSelectedEmployee() { return selectedEmployee; }
    public void setSelectedEmployee(Employee e) { this.selectedEmployee = e; }
}

