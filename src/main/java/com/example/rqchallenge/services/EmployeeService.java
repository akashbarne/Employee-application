package com.example.rqchallenge.services;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.NoSuchEmployeeExistsException;
import com.example.rqchallenge.models.Employee;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public List<Employee> getAllEmployees() throws EmployeeNotFoundException;

    public List<Employee> getEmployeesByNameSearch(String searchString) throws NoSuchEmployeeExistsException;

    public Employee getEmployeeById(String id) throws NoSuchEmployeeExistsException;

    public Integer getHighestSalaryOfEmployees();

    public List<String> getTopTenHighestEarningEmployeeNames();

    public Employee createEmployee(Map<String, Object> employee);

    public String deleteEmployeeById(@PathVariable String id) throws EmployeeNotFoundException;

}
