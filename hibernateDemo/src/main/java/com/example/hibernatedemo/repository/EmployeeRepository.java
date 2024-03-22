package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.pojo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.department.department_id = :department_id")
    List<Employee> findByIdWithDepartment(@Param("department_id") int department_id);

    // for testing criteria query
    @Query("SELECT e from Employee e where e.email like %:format")
    List<Employee> findByEmail(@Param("format") String format);
}
