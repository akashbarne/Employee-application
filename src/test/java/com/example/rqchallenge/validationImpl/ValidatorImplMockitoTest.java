package com.example.rqchallenge.validationImpl;

import com.example.rqchallenge.exception.BadEmployeeRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ValidatorImplMockitoTest {

    @InjectMocks
    private ValidatorImpl validator;

    @Test
    public void validateEmployeeObjectNameMissingTest() {
        //setup
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_age", "25");
        m1.put("employee_salary", "2500000");
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> validator.validateEmployeeObject(m1));
    }
    @Test
    public void validateEmployeeObjectAgeMissingTest() {
        //setup
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_name", "name");
        m1.put("employee_salary", "2500000");
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> validator.validateEmployeeObject(m1));
    }

    @Test
    public void validateEmployeeObjectSalaryMissingTest() {
        //setup
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_name", "name");
        m1.put("employee_age", "25");
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> validator.validateEmployeeObject(m1));
    }

    @Test
    public void validateEmployeeObjectAgeNegativeTest() {
        //setup
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_name", "name");
        m1.put("employee_age", "-25");
        m1.put("employee_salary", "2500000");
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> validator.validateEmployeeObject(m1));
    }

    @Test
    public void validateEmployeeObjectSalaryNegativeTest() {
        //setup
        Map<String, Object> m1 = new HashMap<>();
        m1.put("employee_name", "name");
        m1.put("employee_age", "25");
        m1.put("employee_salary", "-2500000");
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> validator.validateEmployeeObject(m1));
    }

    @Test
    public void validateEmployeeObjectIdProvidedTest() {
        //setup
        Map<String, Object> m1 = new HashMap<>();
        m1.put("id", "2");
        m1.put("employee_name", "name");
        m1.put("employee_age", "25");
        m1.put("employee_salary", "2500000");
        //execution
        Assertions.assertThrows(BadEmployeeRequest.class,() -> validator.validateEmployeeObject(m1));
    }

}
