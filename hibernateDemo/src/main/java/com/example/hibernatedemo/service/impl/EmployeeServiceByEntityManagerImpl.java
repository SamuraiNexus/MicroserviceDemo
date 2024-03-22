package com.example.hibernatedemo.service.impl;

import com.example.hibernatedemo.pojo.entity.Employee;
import com.example.hibernatedemo.repository.EmployeeRepositoryEntityManager;
import com.example.hibernatedemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceByEntityManagerImpl implements EmployeeService {
    private final EmployeeRepositoryEntityManager entityManager;

    @Autowired
    public EmployeeServiceByEntityManagerImpl(EmployeeRepositoryEntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public Employee findById(int id) {
        return entityManager.findById(id);
    }
    @Override
    public List<Employee> findAllEmployees() {
        return entityManager.findAllEmployee();
    }

    @Override
    @Transactional
    public int saveEmployee(Employee employee) {
        int insert_Id = employee.getEmployee_id();
        if (findById(insert_Id) == null){
            entityManager.saveEmployee(employee);
        }
        return insert_Id;
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        entityManager.updateEmployee(employee);
    }


    @Override
    public List<Employee> findEmployeeWithDepartmentId(int department_id) {
        return entityManager.getEmployeeByDepartmentId(department_id);
    }

    @Override
    public List<Employee> findEmployeeByEmailFormat(String format) {
        return entityManager.getEmployeeByEmail(format);
    }
}
