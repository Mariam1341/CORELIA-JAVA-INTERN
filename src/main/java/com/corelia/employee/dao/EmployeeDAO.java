package com.corelia.employee.dao;

import com.corelia.employee.entity.Employee;
import javax.persistence.*;
import java.util.List;

public class EmployeeDAO {

	 private EntityManagerFactory emf;

	    public EmployeeDAO() {
	        emf = Persistence.createEntityManagerFactory("EmployeePU");
	    }

	    private EntityManager em() {
	        return emf.createEntityManager();
	    }

	    public List<Employee> findAll() {
	        EntityManager em = em();
	        try {
	            return em.createQuery("FROM Employee", Employee.class)
	                     .getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    public void save(Employee e) {
	        EntityManager em = em();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            if (e.getId() == null) em.persist(e);
	            else em.merge(e);
	            tx.commit();
	        } finally {
	            if (tx.isActive()) tx.rollback();
	            em.close();
	        }
	    }

	    public void delete(Long id) {
	        EntityManager em = em();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            Employee e = em.find(Employee.class, id);
	            if (e != null) em.remove(e);
	            tx.commit();
	        } finally {
	            if (tx.isActive()) tx.rollback();
	            em.close();
	        }
	    }

}
