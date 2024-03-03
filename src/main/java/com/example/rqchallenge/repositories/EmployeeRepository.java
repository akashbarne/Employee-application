package com.example.rqchallenge.repositories;

import com.example.rqchallenge.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    @Query("select e from Employee e where employee_name = :name")
    List<Employee> getEmployeeByName(@Param("name") String searchString);

    @Query("select max(employee_salary) from Employee")
    Integer getHighestSalaryOfEmployees();

    @Query(value="select employee_name,id,employee_salary,employee_age,profile_image from Employee order by employee_salary desc limit 10", nativeQuery = true)
    List<Employee> findTop10ByOrderByEmployeeSalary_Desc();

}
