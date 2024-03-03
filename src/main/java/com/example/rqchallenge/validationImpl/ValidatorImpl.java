package com.example.rqchallenge.validationImpl;

import com.example.rqchallenge.controllers.EmployeeController;
import com.example.rqchallenge.exception.BadEmployeeRequest;
import com.example.rqchallenge.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValidatorImpl implements Validator {

    private final Logger logger = LoggerFactory.getLogger(ValidatorImpl.class);

    @Override
    public void validateEmployeeObject(Map<String, Object> employee) {
        if(employee.get("employee_name") == null || employee.get("employee_age") == null || employee.get("employee_salary") == null) {
            logger.error("Employee name, age, salary are mandatory field");
            throw new BadEmployeeRequest("Employee name, age, salary are mandatory field");
        } else if(Integer.parseInt(employee.get("employee_age").toString()) <= 0) {
            logger.error("Employee age should be greater than 0");
            throw new BadEmployeeRequest("Employee age should be greater than 0");
        } else if(Integer.parseInt(employee.get("employee_salary").toString()) <= 0) {
            logger.error("Employee salary should be greater than 0");
            throw new BadEmployeeRequest("Employee salary should be greater than 0");
        } else if(employee.get("id") != null) {
            logger.error("Employee Id is a auto generated field do not send it");
            throw new BadEmployeeRequest("Employee Id is a auto generated field do not send it");
        }
    }

}
