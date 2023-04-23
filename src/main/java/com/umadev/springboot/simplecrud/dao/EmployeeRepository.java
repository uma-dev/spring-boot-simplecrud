package com.umadev.springboot.simplecrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umadev.springboot.simplecrud.entity.Employee;

// You can change the name of the endpoint with
// @RepositoryRestResource(path="othername")

// JpaRepository<entity type, primary key> 
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
