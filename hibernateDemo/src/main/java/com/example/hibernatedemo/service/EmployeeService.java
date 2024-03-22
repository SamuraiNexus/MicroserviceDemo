package com.example.hibernatedemo.service;


import com.example.hibernatedemo.pojo.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
     Employee findById(int id);
          List<Employee> findAllEmployees();

     int saveEmployee(Employee employee);
     void updateEmployee(Employee employee);
     List<Employee> findEmployeeWithDepartmentId(int department_id);
     List<Employee> findEmployeeByEmailFormat(String format);

}
