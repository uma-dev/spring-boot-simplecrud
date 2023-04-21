package com.umadev.springboot.simplecrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umadev.springboot.simplecrud.dao.EmployeeDAO;
import com.umadev.springboot.simplecrud.entity.Employee;

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
}
