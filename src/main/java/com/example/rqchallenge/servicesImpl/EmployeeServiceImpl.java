package com.example.rqchallenge.servicesImpl;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.NoSuchEmployeeExistsException;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.repositories.EmployeeRepository;
import com.example.rqchallenge.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(employeeList::add);
        if(!employeeList.isEmpty()) {
            return employeeList;
        } else {
            logger.error("no employees in database");
            throw new EmployeeNotFoundException("No Employees in Database");
        }

    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String searchString) throws NoSuchEmployeeExistsException {
        List<Employee> employeeList = employeeRepository.getEmployeeByName(searchString);
        if(!employeeList.isEmpty()) {
            return employeeList;
        } else {
            logger.error("employee does not exist");
            throw new NoSuchEmployeeExistsException("Employee does not exist with name: " + searchString);
        }
    }

    @Override
    public Employee getEmployeeById(String id) throws NoSuchEmployeeExistsException{
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            logger.error("Employee does not exist");
            throw new NoSuchEmployeeExistsException("Employee does not exist with Id: " + id);
        }
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
        return employeeRepository.getHighestSalaryOfEmployees();
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        List<String> employeeNameList = new ArrayList<>();
        employeeRepository.findTop10ByOrderByEmployeeSalary_Desc().forEach(emp-> employeeNameList.add(emp.getEmployee_name()));
        return employeeNameList;
    }

    @Override
    public Employee createEmployee(Map<String, Object> employee) {
        Employee emp1 = Employee.builder()
                .employee_name(employee.get("employee_name").toString())
                .employee_age(employee.get("employee_age").toString())
                .employee_salary(employee.get("employee_salary").toString())
                .profile_image((employee.get("profile_image")!=null?employee.get("profile_image"):"").toString())
                .build();
        return employeeRepository.save(emp1);
    }

    @Override
    public String deleteEmployeeById(String id) throws EmployeeNotFoundException{
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return "Successfully deleted employee: " + employee.get().getEmployee_name();
        } else {
            logger.error("employee not found");
            throw new EmployeeNotFoundException("Employee not found with Id: " + id);
        }
    }
}
