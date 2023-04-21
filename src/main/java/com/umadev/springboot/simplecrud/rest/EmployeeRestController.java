package com.umadev.springboot.simplecrud.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umadev.springboot.simplecrud.entity.Employee;
import com.umadev.springboot.simplecrud.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    // Inject employee DAO
    private EmployeeService employeeService;

    // Constructor injection
    public EmployeeRestController( EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // Expose /employees to return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
}
