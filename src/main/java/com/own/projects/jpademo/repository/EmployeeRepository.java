package com.own.projects.jpademo.repository;

import java.util.List;

import com.own.projects.jpademo.entity.Employee;
import com.own.projects.jpademo.entity.FullTimeEmployee;
import com.own.projects.jpademo.entity.PartTimeEmployee;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	//insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}

	//retrieve all Employee -- wont work with Mapped super class as its not an entity
//	public List<Employee> retrieveAllEmployees() {
//			return em.createQuery("select e from Employee e", Employee.class).getResultList();
//		}

	public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}

	public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}

}