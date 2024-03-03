package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonProperty("id")
    private String id;

    @Column
    @NotNull
    @JsonProperty("employee_name")
    private String employee_name;

    @Column
    @JsonProperty("employee_age")
    private String employee_age;

    @Column
    @JsonProperty("employee_salary")
    private String employee_salary;

    @Column
    @JsonProperty(value = "profile_image", defaultValue = "")
    private String profile_image;

}
