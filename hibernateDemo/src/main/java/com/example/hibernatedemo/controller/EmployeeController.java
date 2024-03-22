package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.pojo.dto.EmployeeDTO;
import com.example.hibernatedemo.pojo.entity.Employee;
import com.example.hibernatedemo.service.EmployeeService;
import com.mysql.cj.x.protobuf.Mysqlx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Api(tags = "Employees data api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApiOperation(value = "find all employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        List<EmployeeDTO> list = employees.stream().map((e) -> {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmail(e.getEmail());
            employeeDTO.setEmployee_id(e.getEmployee_id());
            employeeDTO.setDepartment_id(e.getDepartment().getDepartment_id());
            return employeeDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value ="find employee by id", notes = "find employee by id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "employee id", required = true)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_id(employee.getEmployee_id());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDepartment_id(employee.getDepartment().getDepartment_id());
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create new employee", notes = "create user based on body")
    @ApiImplicitParam(paramType = "query", name ="employee", dataTypeClass = Employee.class)
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        int id = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "update new employee", notes = "update user based on body")
    @ApiImplicitParam(paramType = "query", name ="employee", dataTypeClass = Employee.class)
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/departments")
    @ApiOperation(value ="find list of employees by department id", notes = "find employee by id")
    @ApiImplicitParam(paramType = "path", name = "department id", value = "department id", required = true)
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@RequestParam int department_id) {
        List<Employee> employees = employeeService.findEmployeeWithDepartmentId(department_id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value ="find list of employees by emailFormat", notes = "find employee by emailFormat")
    @ApiImplicitParam(paramType = "path", name = "emailFormat", value = "email Format", required = true)
    public ResponseEntity<List<Employee>> getEmployeesByEmailsFormat(@RequestParam String emailFormat) {
        List<Employee> employees = employeeService.findEmployeeByEmailFormat(emailFormat);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
