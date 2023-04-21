package com.umadev.springboot.simplecrud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    // As of Spring Framework 4.3, an @Autowired annotation on such a
    // constructor is no longer necessary if the target bean only defines
    // one constructor to begin with. However, if several constructors 
    //are available, at least one must be annotated to teach the 
    //container which one to use.
    @Autowired
    public EmployeeRestController( EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // Expose /employees to return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // Add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        
        Employee theEmployee = employeeService.findById(employeeId);

         // if not found
        if(theEmployee == null ){
            throw new RuntimeException("Employee ID not found");
        }

        return theEmployee;
    }
    
}
