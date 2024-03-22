package com.example.hibernatedemo.repository;


import com.example.hibernatedemo.pojo.entity.Department;
import com.example.hibernatedemo.pojo.entity.Employee;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class EmployeeRepositoryEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> findAllEmployee() {
        TypedQuery<Employee> query =
                entityManager.createQuery("select e from Employee e", Employee.class);
        List<Employee> list = query.getResultList();
        return list;
    }

    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    public List<Employee> getEmployeeByDepartmentId(int departmentId) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "select e from Employee e join Department d where d.department_id =:departmentid",
                Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }
}
