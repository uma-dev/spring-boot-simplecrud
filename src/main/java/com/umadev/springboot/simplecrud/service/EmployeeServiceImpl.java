package com.umadev.springboot.simplecrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umadev.springboot.simplecrud.dao.EmployeeRepository;
import com.umadev.springboot.simplecrud.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    
    // Add DAO with constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            throw new RuntimeException("Did not find employee ID: " + theId);
        }
        return theEmployee;
    }
 
    // Best practice: to use the service layer, so the @Transactional
    // will be here in the service layer instead of DAO
    // Doesn't need @Transactional as its implemented by spring data jpa
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    // Doesn't need @Transactional as its implemented by spring data jpa
    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
