package com.example.rqchallenge.controllers;

import com.example.rqchallenge.exception.BadEmployeeRequest;
import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.NoSuchEmployeeExistsException;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.Response;
import com.example.rqchallenge.services.EmployeeService;
import com.example.rqchallenge.validation.Validator;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class EmployeeControllerMockitoTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private Validator validator;

    @InjectMocks
    private EmployeeController employeeController;



    @Test
    public void getAllEmployeesTest() {
        //setup
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("2","name","2","25000",""));
        when(employeeService.getAllEmployees()).thenReturn((employeeList));

        //execution
        ResponseEntity<?> response = employeeController.getAllEmployees();
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success",employeeList)), HttpStatus.OK));
    }

    @Test
    public void getEmployeesByNameSearchTest() {
        //setup
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("2","name","2","25000",""));
        when(employeeService.getEmployeesByNameSearch("name")).thenReturn(employeeList);

        //execution
        ResponseEntity<?> response = employeeController.getEmployeesByNameSearch("name");
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success",employeeList)), HttpStatus.OK));
    }

    @Test
    public void getEmployeeByIdTest() {
        //setup
        Employee e1 = new Employee("2","name","2","25000","");
        when(employeeService.getEmployeeById("2")).thenReturn(e1);

        //execution
        ResponseEntity<?> response = employeeController.getEmployeeById("2");
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success",e1)), HttpStatus.OK));
    }

    @Test
    public void getHighestSalaryOfEmployeesTest() {
        //setup
        when(employeeService.getHighestSalaryOfEmployees()).thenReturn(25000);

        //execution
        ResponseEntity<?> response = employeeController.getHighestSalaryOfEmployees();
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success",25000)), HttpStatus.OK));
    }

    @Test
    public void getTopTenHighestEarningEmployeeNamesTest() {
        //setup
        ArrayList<String> employeeNameList = new ArrayList<>();
        employeeNameList.add("name1");
        employeeNameList.add("name2");
        employeeNameList.add("name3");
        employeeNameList.add("name4");
        employeeNameList.add("name5");
        employeeNameList.add("name6");
        employeeNameList.add("name7");
        employeeNameList.add("name8");
        employeeNameList.add("name9");
        employeeNameList.add("name10");
        when(employeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(employeeNameList);

        //execution
        ResponseEntity<?> response = employeeController.getTopTenHighestEarningEmployeeNames();
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success",employeeNameList)), HttpStatus.OK));
    }

    @Test
    public void createEmployeeTest() {
        //setup
        Employee e1 = new Employee("2","name","25","2500000","");
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_name", "name");
        m1.put("employee_age", "25");
        m1.put("employee_salary", "2500000");
        when(employeeService.createEmployee(m1)).thenReturn(e1);

        //execution
        ResponseEntity<?> response = employeeController.createEmployee(m1);
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success",e1)), HttpStatus.CREATED));
    }

    @Test
    public void deleteEmployeeByIdTest() {
        //setup
        when(employeeService.deleteEmployeeById("2")).thenReturn("Successfully deleted employee: 2");

        //execution
        ResponseEntity<?> response = employeeController.deleteEmployeeById("2");
        assertThat(response).isEqualTo(new ResponseEntity<>(new Gson().toJson(new Response("success","Successfully deleted employee: 2")), HttpStatus.OK));
    }


    // Error test cases
    @Test()
    public void getAllEmployeesErrorTest() {
        //setup
        when(employeeService.getAllEmployees()).thenThrow(new EmployeeNotFoundException("No Employees in Database"));
        //execution
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeController.getAllEmployees());
    }

    @Test
    public void getEmployeesByNameSearchErrorTest() {
        //setup
        when(employeeService.getEmployeesByNameSearch(anyString())).thenThrow(new NoSuchEmployeeExistsException("Employee does not exist with name: name"));
        //execution
        Assertions.assertThrows(NoSuchEmployeeExistsException.class,() -> employeeController.getEmployeesByNameSearch(anyString()));
    }

    @Test
    public void getEmployeeByIdErrorTest() {
        //setup
        when(employeeService.getEmployeeById(anyString())).thenThrow(new NoSuchEmployeeExistsException("Employee does not exist with Id: 2"));
        //execution
        Assertions.assertThrows(NoSuchEmployeeExistsException.class,() -> employeeController.getEmployeeById(anyString()));
    }

    @Test
    public void createEmployeeErrorTest() {
        //setup
        doThrow(BadEmployeeRequest.class).when(validator).validateEmployeeObject(anyMap());
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> employeeController.createEmployee(anyMap()));
    }

    @Test
    public void deleteEmployeeByIdErrorTest() {
        //setup
        when(employeeService.deleteEmployeeById(anyString())).thenThrow(new EmployeeNotFoundException("Employee not found with Id: 2"));
        //execution
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeController.deleteEmployeeById(anyString()));
    }
}
