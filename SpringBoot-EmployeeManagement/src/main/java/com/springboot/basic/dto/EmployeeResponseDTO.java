package com.springboot.basic.dto;

import java.time.LocalDateTime;

import com.springboot.basic.Enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

	private Integer empId;
	
	private String empName;
	
	private String empDept;
	
	private Double salary;
	
	private Integer age;
	
	private String city;
	
	private String state;
	
	private Role role;

	private String username;
	
	
	//Audit fields
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String updatedBy;
	private String createdBy;
	private Long version;



}
