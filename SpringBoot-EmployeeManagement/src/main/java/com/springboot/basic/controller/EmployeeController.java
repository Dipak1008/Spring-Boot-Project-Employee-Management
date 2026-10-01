package com.springboot.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.basic.Entity.Employee;
import com.springboot.basic.Enums.Role;
import com.springboot.basic.dto.EmployeeRequestDTO;
import com.springboot.basic.dto.EmployeeResponseDTO;
import com.springboot.basic.dto.LoginRequestDTO;
import com.springboot.basic.dto.LoginResponseDTO;
import com.springboot.basic.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	// Insert employee
	
	// swagger 
	@Operation(
			summary = "Insert and update new employee details",
			description = "Inserting and updating employee details")
	@ApiResponse(responseCode = "200", description = "Employee Inserted/Updated")
	
	@PostMapping("/insertEmp")
	public ResponseEntity<EmployeeResponseDTO> createOrUpdateEmployee(
			@Valid @RequestBody EmployeeRequestDTO dto) {
		 EmployeeResponseDTO response = service.createOrUpdateEmployee(dto);
		 HttpHeaders headers1 = new HttpHeaders();
		 if(dto.getEmpId() == null) {
			 headers1.add("Operation", "Employee Insert");
			 headers1.add("Status", "Created");
		 }else {
			 headers1.add("Operation", "Employee Update");
			 headers1.add("Status", "Updated");
		 }
		return new ResponseEntity<>(response, headers1, HttpStatus.CREATED );
		
	}
	
	// get all Employee 
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployeeDetails() {
		List<EmployeeResponseDTO> response = service.getAllEmployeeDetails();
		HttpHeaders headers2 = new HttpHeaders(); 
		headers2.add("Operation", "Get All Employees");
		headers2.add("Status", "Success");
		return new ResponseEntity<>(response,headers2, HttpStatus.OK);
		
	}
	
	
	// Get by employee id
	@GetMapping("/getById/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable int id) {
		 EmployeeResponseDTO response = service.getEmployeeById(id);
		 HttpHeaders headers3 = new HttpHeaders(); 
		 headers3.add("Operation", "Get Employee By ID"); 
		 headers3.add("Employee-ID", String.valueOf(id));
		 
		return new ResponseEntity<>(response,headers3, HttpStatus.OK);
		
	}

	
//	// update employee
//	@PutMapping("/update/{id}")
//	public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(
//			      @PathVariable Integer id,@Valid @RequestBody EmployeeRequestDTO dto) {
//		 EmployeeResponseDTO response = service.updateEmployeeById(id, dto);
//		 HttpHeaders headers4 = new HttpHeaders(); 
//		 headers4.add("Operation", "Employee Update");
//		 headers4.add("Employee-ID", String.valueOf(id));
//		return new ResponseEntity<>(response,headers4, HttpStatus.OK);
//		
//	}

	
	// delete employee by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
		 service.deleteEmployeeById(id);
		 HttpHeaders headers5 = new HttpHeaders();
		 headers5.add("Operation", "Employee Delete"); 
		 headers5.add("Employee-ID", String.valueOf(id));
		return new ResponseEntity<>("Employee deleted successfully",headers5, HttpStatus.OK); 
		
	}

	
	// count all employee
	@GetMapping("/count")
	public ResponseEntity<Long> getCountEmployee() {
		long response = service.getCountEmployee();
		HttpHeaders headers6 = new HttpHeaders();
		headers6.add("Operation", "Employee Count"); 
		headers6.add("Status", "Success");
		return new ResponseEntity<>(response,headers6, HttpStatus.OK);
		
	}
    
	
	// Get employee by name
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String empName) {
		List<Employee> response = service.getEmployeeByName(empName);
		HttpHeaders headers7 = new HttpHeaders();
		headers7.add("Operation", "Search By Name"); 
		headers7.add("Employee-Name", empName);
		return new ResponseEntity<>(response,headers7, HttpStatus.OK);
		
	}
	
	
	// get employee by dept
	@GetMapping("/dept/{dept}")
	public ResponseEntity<List<Employee>> getByEmpDept(@PathVariable ("dept") String empDept) {
		List<Employee> response = service.getByEmpDept(empDept);
		HttpHeaders headers8 = new HttpHeaders(); 
		headers8.add("Operation", "Search By Department"); 
		headers8.add("Department", empDept);
		return new ResponseEntity<>(response,headers8, HttpStatus.OK);
		
	}

     // get employee by city
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Employee>> getByCity(@PathVariable String city) {
		List<Employee> response = service.getByCity(city);
		HttpHeaders headers9 = new HttpHeaders(); 
		headers9.add("Operation", "Search By City"); 
		headers9.add("City", city);
		return new ResponseEntity<>(response,headers9, HttpStatus.OK);
		
	}


	// get employee by state
	@GetMapping("/state/{state}")
	public ResponseEntity<List<Employee>> getByState(@PathVariable String state) {
		List<Employee> response = service.getByState(state);
		HttpHeaders headers10 = new HttpHeaders();
		headers10.add("Operation", "Search By State");
		headers10.add("State", state);
		return new ResponseEntity<>(response,headers10, HttpStatus.OK);
		
	}

	
	// get employee by age
	@GetMapping("/age/{age}")
	public ResponseEntity<List<Employee>> getByAgeGreaterThan(@PathVariable Integer age) {
		List<Employee> response = service.getByAgeGreaterThan(age);
		HttpHeaders headers11 = new HttpHeaders();
		headers11.add("Operation", "Search By Age"); 
		headers11.add("Age", String.valueOf(age));
		return new ResponseEntity<>(response,headers11, HttpStatus.OK);
		
	}

	
	// get employee by salary
	@GetMapping("/salary/{salary}")
	public ResponseEntity<List<Employee>> getBySalaryGreaterThan(@PathVariable Double salary) {
		List<Employee> response = service.getBySalaryGreaterThan(salary);
		HttpHeaders headers12 = new HttpHeaders();
		headers12.add("Operation", "Search By Salary"); 
		headers12.add("Salary", String.valueOf(salary));
		return new ResponseEntity<>(response, headers12, HttpStatus.OK);
		
	}

	
	@GetMapping("/search/{name}")
	public ResponseEntity< List<Employee>> searchByName(@PathVariable("name") String empName) {
		 List<Employee> response = service.searchByName(empName);
		 HttpHeaders headers13 = new HttpHeaders(); 
		 headers13.add("Operation", "Employee Name Search"); 
		 headers13.add("Search-Text", empName);
			return new ResponseEntity<>(response,headers13, HttpStatus.OK);
		
	}

	
	// Login by User
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
		LoginResponseDTO response = service.login(request);
		HttpHeaders headers14 = new HttpHeaders(); 
		headers14.add("Operation", "Employee Login"); 
		headers14.add("Login-Status", "Success");
		return new ResponseEntity<>(response,headers14, HttpStatus.OK);
		
	}
	
	
	// get employee by role
	@GetMapping("/role/{role}")
	public ResponseEntity<List<Employee>> getEmployeeByRole(@PathVariable Role role) {
		List<Employee> response = service.getEmployeeByRole(role);
		HttpHeaders headers15 = new HttpHeaders();
		headers15.add("Operation", "Search By Role"); 
		headers15.add("Role", role.name());
		return new ResponseEntity<>(response, headers15, HttpStatus.OK);
		
	}

	
	// pagination using get all employee
	@GetMapping("/page") 
	public ResponseEntity<Page<Employee>> getEmployees( 
			@RequestParam int pageNo, @RequestParam int pageSize) {
		Page<Employee> response = service.getEmployees(pageNo, pageSize);
		return new ResponseEntity<>( response, HttpStatus.OK);
		
	}
	
	
	// pagination + sorting using field salary
	@GetMapping("/page-sort") 
	public ResponseEntity<Page<Employee>> getEmployeesSorted( 
			@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String field) {
		Page<Employee> response = service.getEmployeesSorted( pageNo, pageSize, field);
		return new ResponseEntity<>( response, HttpStatus.OK);
		
	} 
	
	
	// pagination using salary
	@GetMapping("/salary-page") 
	public ResponseEntity<Page<Employee>> getBySalary( 
			@RequestParam Double salary, @RequestParam int pageNo, @RequestParam int pageSize) {
		Page<Employee> response = service.getBySalary( salary, pageNo, pageSize); 
		return new ResponseEntity<>( response, HttpStatus.OK); 
		
	} 
	
	
	// pagination + sorting salary using field 
	@GetMapping("/salary-page-sort") 
	public ResponseEntity<Page<Employee>> getBySalarySorted( 
		@RequestParam Double salary, @RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String field) { 
		Page<Employee> response = service.getBySalarySorted( salary, pageNo, pageSize, field);
		return new ResponseEntity<>( response, HttpStatus.OK); 
		
	} 
	
	
	// JPQL Query
	@GetMapping("/query/age/{age}")
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByAge(@PathVariable Integer age) {
		return ResponseEntity.ok(service.getEmployeesByAge(age));
		
	}

	
	// Native sql query
	@GetMapping("/query/salary/{salary}")
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesBySalary(@PathVariable Double salary) {
		return ResponseEntity.ok(service.getEmployeesBySalary(salary));
		
	}

	
	// Named query
	@GetMapping("/query/empDept/{empDept}")
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByDepartment(@PathVariable String empDept) {
		return ResponseEntity.ok(service.getEmployeesByDepartment(empDept));
		
	}

     }

