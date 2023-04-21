package com.umadev.springboot.simplecrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umadev.springboot.simplecrud.dao.EmployeeDAO;
import com.umadev.springboot.simplecrud.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;
    
    // Add DAO with constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }
 
    // Best practice: to use the service layer, so the @Transactional
    // will be here in the service layer instead of DAO
    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }
}
