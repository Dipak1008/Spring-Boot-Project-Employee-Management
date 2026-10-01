package com.springboot.basic.service;

import java.util.List;

import com.springboot.basic.Entity.Employee;
import com.springboot.basic.Enums.Role;
import com.springboot.basic.dto.EmployeeRequestDTO;
import com.springboot.basic.dto.EmployeeResponseDTO;
import com.springboot.basic.dto.LoginRequestDTO;
import com.springboot.basic.dto.LoginResponseDTO;

public interface EmpService {

	// Abstract methods
	
    EmployeeResponseDTO createOrUpdateEmployee(EmployeeRequestDTO dto);
    
    List<EmployeeResponseDTO> getAllEmployeeDetails();
    
    EmployeeResponseDTO getEmployeeById(int id);
    
//    EmployeeResponseDTO updateEmployeeById(Integer id, EmployeeRequestDTO dto);
    
    void deleteEmployeeById(Integer id);
    
    long getCountEmployee();
    
    List<Employee> getEmployeeByName(String empName);
    
    List<Employee> getByEmpDept(String empDept);
    
    List<Employee> getByCity(String city);
    
    List<Employee> getByState(String state);
    
    List<Employee> getByAgeGreaterThan(Integer age);
    
    List<Employee> getBySalaryGreaterThan(Double salary);
    
    List<Employee> searchByName(String empName);
    
    // login User 
    LoginResponseDTO login(LoginRequestDTO request);
    
    List<Employee> getEmployeeByRole(Role role);
    
    
    // native named queries abstract method
    List<EmployeeResponseDTO> getEmployeesByAge(Integer age);
    
    List<EmployeeResponseDTO> getEmployeesBySalary(Double salary);
    
    List<EmployeeResponseDTO> getEmployeesByDepartment(String empDept);
}