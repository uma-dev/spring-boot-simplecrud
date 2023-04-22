package com.umadev.springboot.simplecrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umadev.springboot.simplecrud.entity.Employee;

// JpaRepository<entity type, primary key>
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
