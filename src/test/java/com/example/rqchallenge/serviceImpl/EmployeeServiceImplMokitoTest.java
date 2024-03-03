package com.example.rqchallenge.serviceImpl;

import com.example.rqchallenge.exception.EmployeeNotFoundException;
import com.example.rqchallenge.exception.NoSuchEmployeeExistsException;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.repositories.EmployeeRepository;
import com.example.rqchallenge.servicesImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplMokitoTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void getAllEmployeesTest() {
        //setup
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("2","name","2","25000",""));
        when(employeeRepository.findAll()).thenReturn((employeeList));

        //execution
        List<Employee> response = employeeService.getAllEmployees();
        assertThat(response).isEqualTo(employeeList);
    }


    @Test
    public void getEmployeesByNameSearchTest() {
        //setup
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("2","name","2","25000",""));
        when(employeeRepository.getEmployeeByName("name")).thenReturn(employeeList);

        //execution
        List<Employee> response = employeeService.getEmployeesByNameSearch("name");
        assertThat(response).isEqualTo(employeeList);
    }

    @Test
    public void getEmployeeByIdTest() {
        //setup
        Employee e1 = new Employee("2","name","2","25000","");
        when(employeeRepository.findById(anyString())).thenReturn(Optional.of(e1));

        //execution
        Employee response = employeeService.getEmployeeById("2");
        assertThat(response).isEqualTo(e1);
    }

    @Test
    public void getHighestSalaryOfEmployeesTest() {
        //setup
        when(employeeRepository.getHighestSalaryOfEmployees()).thenReturn(2500000);

        //execution
        int response = employeeService.getHighestSalaryOfEmployees();
        assertThat(response).isEqualTo(2500000);
    }

    @Test
    public void getTopTenHighestEarningEmployeeNamesTest() {
        //setup
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1","name1","1","25000",""));
        employeeList.add(new Employee("2","name2","1","25000",""));
        employeeList.add(new Employee("3","name3","1","25000",""));
        employeeList.add(new Employee("4","name4","1","25000",""));
        employeeList.add(new Employee("5","name5","1","25000",""));
        employeeList.add(new Employee("6","name6","1","25000",""));
        employeeList.add(new Employee("7","name7","1","25000",""));
        employeeList.add(new Employee("8","name8","1","25000",""));
        employeeList.add(new Employee("9","name9","1","25000",""));
        employeeList.add(new Employee("10","name10","1","25000",""));
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

        when(employeeRepository.findTop10ByOrderByEmployeeSalary_Desc()).thenReturn(employeeList);

        //execution
        List<String> response = employeeService.getTopTenHighestEarningEmployeeNames();
        assertThat(response).isEqualTo(employeeNameList);
    }

    @Test
    public void createEmployeeTest() {
        //setup
        Employee e1 = new Employee("2","name","25","2500000","");
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_name", "name");
        m1.put("employee_age", "25");
        m1.put("employee_salary", "2500000");
        when(employeeRepository.save(any(Employee.class))).thenReturn(e1);
        //execution
        Employee response = employeeService.createEmployee(m1);
        assertThat(response).isEqualTo(e1);
    }

    @Test
    public void deleteEmployeeByIdTest() {
        //setup
        Employee e1 = new Employee("2","name","25","2500000","");
        when(employeeRepository.findById(anyString())).thenReturn(Optional.of(e1));
        doNothing().when(employeeRepository).deleteById(anyString());

        //execution
        String response = employeeService.deleteEmployeeById("2");
        assertThat(response).isEqualTo("Successfully deleted employee: name");
    }


    //Error Test cases
    @Test
    public void getAllEmployeesErrorTest() {
        //setup
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        //execution
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeService.getAllEmployees());
    }


    @Test
    public void getEmployeesByNameSearchErrorTest() {
        //setup
        when(employeeRepository.getEmployeeByName(anyString())).thenReturn(new ArrayList<>());

        //execution
        Assertions.assertThrows(NoSuchEmployeeExistsException.class,() -> employeeService.getEmployeesByNameSearch(anyString()));
    }

    @Test
    public void getEmployeeByIdErrorTest() {
        //setup
        when(employeeRepository.findById(anyString())).thenReturn(Optional.empty());

        //execution
        Assertions.assertThrows(NoSuchEmployeeExistsException.class,() -> employeeService.getEmployeeById(anyString()));
    }

    @Test
    public void deleteEmployeeByIdErrorTest() {
        //setup
        Employee e1 = new Employee("2","name","25","2500000","");
        when(employeeRepository.findById(anyString())).thenReturn(Optional.empty());

        //execution
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeService.deleteEmployeeById(anyString()));
    }
}
