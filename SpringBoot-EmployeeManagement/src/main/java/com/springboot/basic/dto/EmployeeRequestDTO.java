package com.springboot.basic.dto;

import com.springboot.basic.Enums.Role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class EmployeeRequestDTO {
	
	private Integer empId;
	
	@NotBlank(message = "Employee name is required. Not Empty")
	private String empName;
	
	@NotBlank(message = "Department name is required ")
	private String empDept;
	
	@DecimalMin(value = "18000.0", inclusive = false, message = "Salary is must be greater than 18000 ")
	@DecimalMax(value = "100000.0", message = "Salary must be below Rs.100000")
	private Double salary;
	
	@Min(value = 18, message = "Age is above 18 required")
	@Max(value = 50, message = "Age is must be  below 50 required")
	private Integer age;
	
	@NotBlank(message = "City name is required. Not Empty")
	private String city;
	
	@NotBlank(message = "State name is required")
	private String state;
    
	@NotNull(message = "Role is required")
    private Role role;
    
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
