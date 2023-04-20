package com.umadev.springboot.simplecrud.dao;

import java.util.List;

import com.umadev.springboot.simplecrud.entity.Employee;

public interface EmployeeDAO {
    
    List<Employee> findAll();
}
