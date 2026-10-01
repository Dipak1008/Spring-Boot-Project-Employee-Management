package com.springboot.basic.mapper;

import com.springboot.basic.dto.EmployeeRequestDTO;
import com.springboot.basic.dto.EmployeeResponseDTO;

import org.springframework.stereotype.Component;

import com.springboot.basic.Entity.Employee;

@Component
public class EmployeeMapper {
 
	// Request Dto -> Entity
    public static Employee toEntity(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

        employee.setEmpName(dto.getEmpName());
        employee.setEmpDept(dto.getEmpDept());
        employee.setSalary(dto.getSalary());
        employee.setAge(dto.getAge());
        employee.setCity(dto.getCity());
        employee.setState(dto.getState());
        employee.setRole(dto.getRole());
        
        employee.setUsername(dto.getUsername());
        employee.setPassword(dto.getPassword());

        return employee;
    }

    
    // Update entity
    public static void updateEntity(Employee employee, EmployeeRequestDTO dto) {

        employee.setEmpName(dto.getEmpName());
        employee.setEmpDept(dto.getEmpDept());
        employee.setSalary(dto.getSalary());
        employee.setAge(dto.getAge());
        employee.setCity(dto.getCity());
        employee.setState(dto.getState());
        employee.setRole(dto.getRole());

        employee.setUsername(dto.getUsername());
        employee.setPassword(dto.getPassword());
    }

    
    //Entity to response dto
    public static EmployeeResponseDTO toResponseDTO(
            Employee employee) {

        EmployeeResponseDTO response = new EmployeeResponseDTO();

        response.setEmpId(employee.getEmpId());
        response.setEmpName(employee.getEmpName());
        response.setEmpDept(employee.getEmpDept());
        response.setSalary(employee.getSalary());
        response.setAge(employee.getAge());
        response.setCity(employee.getCity());
        response.setState(employee.getState());
        response.setRole(employee.getRole());
        
        response.setUsername(employee.getUsername());

        
     // Audit fields
        response.setCreatedDate(employee.getCreatedDate());
        response.setCreatedBy(employee.getCreatedBy());

        response.setUpdatedDate(employee.getUpdatedDate());
        response.setUpdatedBy(employee.getUpdatedBy());

        response.setVersion(employee.getVersion());

        return response;
    }
}