package com.example.hibernatedemo.service.impl;

import com.example.hibernatedemo.pojo.entity.Department;
import com.example.hibernatedemo.repository.DepartmentRepository;
import com.example.hibernatedemo.service.DepartmentService;
import javax.persistence.Access;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository =departmentRepository;
    }
    @Override
    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        departmentRepository.findAll().forEach((department) -> {
            departmentList.add(department);
        });
        return departmentList;
    }

    @Override
    @Transactional
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void updateDepartment(Department department) {
        if(findById(department.getDepartment_id()).isPresent()) {
            departmentRepository.save(department);
        }
    }
}
