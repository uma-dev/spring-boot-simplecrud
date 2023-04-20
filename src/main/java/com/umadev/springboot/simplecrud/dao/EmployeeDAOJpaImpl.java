package com.umadev.springboot.simplecrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.umadev.springboot.simplecrud.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // Define field for entityManager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl (EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // Return results
        return employees;
    }
}
