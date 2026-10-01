package com.springboot.basic.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.basic.Enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empDetails_table")
@NamedQuery(
	    name = "Employee.findEmployeesByDepartment",
	    query = "SELECT e FROM Employee e WHERE e.empDept = :empDept"
	)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee extends Audit{
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	private String empName;
	private String empDept;
	private Double salary;
	private Integer age;
	private String city;
	private String state;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
