package com.example.rqchallenge.controllers;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.NoSuchEmployeeExistsException;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.Response;
import com.example.rqchallenge.services.EmployeeService;
import com.example.rqchallenge.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;
    @Autowired
    Validator validator;

    @GetMapping()
    ResponseEntity<?> getAllEmployees() throws EmployeeNotFoundException {
        logger.info("get all employees");
        List<Employee> employeeList = employeeService.getAllEmployees();
        logger.debug(employeeList.toString());
        logger.info("employees retrieved: " + employeeList.size());
        return new ResponseEntity<>(new Gson().toJson(new Response("success",employeeList)), HttpStatus.OK);
    }

    @GetMapping("/search/{searchString}")
    ResponseEntity<?> getEmployeesByNameSearch(@PathVariable String searchString) throws NoSuchEmployeeExistsException {
        logger.info("searching for employee with name: " + searchString);
        List<Employee> employeeList = employeeService.getEmployeesByNameSearch(searchString);
        logger.debug(employeeList.toString());
        logger.info("employee found with name: " + searchString);
        return new ResponseEntity<>(new Gson().toJson(new Response("success",employeeList)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getEmployeeById(@PathVariable String id) throws NoSuchEmployeeExistsException{
        logger.info("searching employee with Id: " + id);
        Employee employee = employeeService.getEmployeeById(id);
        logger.debug(employee.toString());
        logger.info("employee found with Id: " + id);
        return new ResponseEntity<>(new Gson().toJson(new Response("success",employee)), HttpStatus.OK);
    }

    @GetMapping("/highestSalary")
    ResponseEntity<?> getHighestSalaryOfEmployees() {
        logger.info("getting highest salary ...");
        int highestSalary = employeeService.getHighestSalaryOfEmployees();
        logger.info("highest salary: " + highestSalary);
        return new ResponseEntity<>(new Gson().toJson(new Response("success",highestSalary)), HttpStatus.OK);
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    ResponseEntity<?> getTopTenHighestEarningEmployeeNames() {
        logger.info("get top ten employee with highest salary");
        List<String> employeeNameList = employeeService.getTopTenHighestEarningEmployeeNames();
        logger.debug("top 10 employee with highest salary" + employeeNameList);
        logger.info("fetched top 10 employees");
        return new ResponseEntity<>(new Gson().toJson(new Response("success", employeeNameList)), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<?> createEmployee(@RequestBody Map<String, Object> employee) {
        logger.info("creating employee ...");
        validator.validateEmployeeObject(employee);
        Employee emp =  employeeService.createEmployee(employee);
        logger.info("Employee created successfully with Id: " + emp.getId());
        logger.debug(emp.toString());
        return new ResponseEntity<>(new Gson().toJson(new Response("success",emp)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployeeById(@PathVariable String id) throws EmployeeNotFoundException {
        logger.info("deleting employee with Id: " + id);
        String emp = employeeService.deleteEmployeeById(id);
        logger.info(emp);
        return new ResponseEntity<>(new Gson().toJson(new Response("success",emp)), HttpStatus.OK);
    }

}
