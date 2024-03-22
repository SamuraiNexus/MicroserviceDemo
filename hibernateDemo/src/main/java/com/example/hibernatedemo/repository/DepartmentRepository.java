package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.pojo.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
