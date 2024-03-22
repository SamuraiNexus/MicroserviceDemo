package com.example.hibernatedemo.pojo.dto;

import com.example.hibernatedemo.pojo.entity.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class EmployeeDTO {
    private int employee_id;
    private String email;
    private int department_id;
}
