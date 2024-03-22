package com.example.hibernatedemo.service;

import com.example.hibernatedemo.pojo.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> findById(int id);
    List<Department> findAll();
    void addDepartment(Department department);
    void updateDepartment(Department department);


}
