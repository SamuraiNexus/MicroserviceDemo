package com.example.hibernatedemo.service.impl;

import com.example.hibernatedemo.pojo.entity.Employee;
import com.example.hibernatedemo.repository.EmployeeRepository;
import com.example.hibernatedemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public int saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee.getEmployee_id();
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (!employeeRepository.findById(employee.getEmployee_id()).isPresent()) {
            return;
        }
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findEmployeeWithDepartmentId(int department_id) {
        return employeeRepository.findByIdWithDepartment(department_id);
    }
}
