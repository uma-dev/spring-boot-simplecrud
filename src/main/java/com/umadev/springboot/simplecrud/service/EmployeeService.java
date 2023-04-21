package com.umadev.springboot.simplecrud.service;

import java.util.List;

import com.umadev.springboot.simplecrud.entity.Employee;

public interface EmployeeService {
    
    List<Employee> findAll();
}
