package com.umadev.springboot.simplecrud.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umadev.springboot.simplecrud.dao.EmployeeDAO;
import com.umadev.springboot.simplecrud.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    // Inject employee DAO
    private EmployeeDAO employeeDAO;

    // Constructor injection
    public EmployeeRestController( EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }

    // Expose /employees to return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }
}
