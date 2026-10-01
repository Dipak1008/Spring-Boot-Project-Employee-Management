package com.springboot.basic.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.basic.Entity.Employee;
import com.springboot.basic.Enums.Role;
import com.springboot.basic.dto.EmployeeRequestDTO;
import com.springboot.basic.dto.EmployeeResponseDTO;
import com.springboot.basic.dto.LoginRequestDTO;
import com.springboot.basic.dto.LoginResponseDTO;
import com.springboot.basic.exception.DuplicateEmployeeException;
import com.springboot.basic.exception.EmployeeNotFoundException;
import com.springboot.basic.mapper.EmployeeMapper;
import com.springboot.basic.repository.EmployeeRepository;


import constants.EmployeeConstants;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService implements EmpService {

	@Autowired
	EmployeeRepository repository;
		
	// Logger 
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	// Insert & update using one post mapping 
	
	@Transactional
	public EmployeeResponseDTO createOrUpdateEmployee(EmployeeRequestDTO dto) {
		 log.info("Create/Update employee request received. Employee ID: {}, Name: {}, Department: {}",
		            dto.getEmpId(), dto.getEmpName(), dto.getEmpDept());

		// check duplicate employee
		if(repository.existsByEmpNameAndEmpDept(dto.getEmpName(), dto.getEmpDept())) {
			log.warn("Duplicate employee found. Name: {}, Department: {}",
	                dto.getEmpName(), dto.getEmpDept());
			
			throw new DuplicateEmployeeException(EmployeeConstants.DUPLICATE_EMPLOYEE);
		}
		
		Employee employee;
		// Create or insert
		if(dto.getEmpId() == null) {
			log.info("Creating new employee. Name: {}, Department: {}",
	                dto.getEmpName(), dto.getEmpDept());
			employee = EmployeeMapper.toEntity(dto);
		}else {
			
			// update employee
			 log.info("Updating employee with ID: {}", dto.getEmpId());
			 
			employee = repository.findById(dto.getEmpId()).orElseThrow(() ->  {
                log.error("Employee not found with ID: {}", dto.getEmpId());
                
                return new RuntimeException("Employee not found with id:" + dto.getEmpId());
            });

			log.info("Employee found. ID: {}, Current Version: {}",
	                employee.getEmpId(), employee.getVersion());
			
		   EmployeeMapper.updateEntity(employee, dto);
		}
		
		Employee savedEmployee = repository.save(employee);
		 log.info("Employee saved successfully. ID: {}, Version: {}",
		            savedEmployee.getEmpId(), savedEmployee.getVersion());

		    log.info("Employee audit details. Created By: {}, Updated By: {}, Created Date: {}, Updated Date: {}",
		            savedEmployee.getCreatedBy(), savedEmployee.getUpdatedBy(),
		            savedEmployee.getCreatedDate(), savedEmployee.getUpdatedDate());
		
		return EmployeeMapper.toResponseDTO(savedEmployee);
	}
	
	
	// Get all Employee data 
	@Cacheable("employees")
	public List<EmployeeResponseDTO> getAllEmployeeDetails() {
		return repository.findAll().stream()
	            .map(EmployeeMapper::toResponseDTO)
	            .toList();
	}
		
	
	
	
	// Get Employee data by id
	@Cacheable(value = "employees", key = "#id")
	public EmployeeResponseDTO getEmployeeById(int id) {
		Employee employee = repository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id));

          return EmployeeMapper.toResponseDTO(employee);
}
	
	
//	// Update Employee By Id
//	public EmployeeResponseDTO updateEmployeeById(Integer id, EmployeeRequestDTO dto) {
//		Employee emp = repository.findById(id).orElseThrow(() ->
//		                 new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id));
//		
//		 EmployeeMapper.updateEntity(emp, dto);
//		
//		 Employee updateEmployee = repository.save(emp);
//		 
//		return EmployeeMapper.toResponseDTO(updateEmployee);
//		 							
//		}
//	
	
		
	// Delete Employee by id 
	@CacheEvict(value = "employees", key = "#id")
	public void deleteEmployeeById(Integer id) {
		Employee emp = repository.findById(id).orElseThrow(() ->
		              new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id));
		    repository.delete(emp);
		 		
		
	}
	
	
	// Count Employees
	public long getCountEmployee() {
		return repository.count();
		
	}

   // find employee by name
	@Override
	@Cacheable(value = "employees", key = "#empName")
	public List<Employee> getEmployeeByName(String empName) {
		return repository.findByEmpName(empName);
	}

  // Search by department name
	@Override
	@Cacheable(value = "employees", key = "#empDept")
	public List<Employee> getByEmpDept(String empDept) {
		return repository.findByEmpDept(empDept);
	}

   // Search by city
	@Override
	public List<Employee> getByCity(String city) {
		return repository.findByCity(city);
	}

   // Search by state
	@Override
	public List<Employee> getByState(String state) {
		return repository.findByState(state);
	}

    // Search by age
	@Override
	public List<Employee> getByAgeGreaterThan(Integer age) {
		return repository.findByAgeGreaterThan(age);
	}

    // search by salary
	@Override
	public List<Employee> getBySalaryGreaterThan(Double salary) {
		return repository.findBySalaryGreaterThan(salary);
	}


	// Employee search by name
	@Override
	public List<Employee> searchByName(String empName) {
		return repository.findByEmpNameContainingIgnoreCase(empName);
	}


	// User Login method
	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
	    Employee employee = repository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
	            .orElseThrow(() ->
	                    new EmployeeNotFoundException(EmployeeConstants.INVALID_LOGIN));

	    return new LoginResponseDTO(EmployeeConstants.LOGIN_SUCCESS, employee.getUsername());
	}

	
	// find employee by role
	@Override
	public List<Employee> getEmployeeByRole(Role role) {
		return repository.findByRole(role);
	}
	
	
	
	// Get paginated employees
	public Page<Employee> getEmployees(int pageNo, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    return repository.findAll(pageable);
	}


	// Get paginated employees sorted by a given field
	public Page<Employee> getEmployeesSorted(int pageNo, int pageSize, String field) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).descending());
	    return repository.findAll(pageable);
	}


	// Get paginated employees filtered by salary
	public Page<Employee> getBySalary(Double salary, int pageNo, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    return repository.findBySalaryGreaterThan(salary, pageable);
	}


	// Get paginated employees filtered by salary and sorted by a given field
	public Page<Employee> getBySalarySorted(Double salary, int pageNo, int pageSize, String field) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).descending());
	    return repository.findBySalaryGreaterThan(salary, pageable);
	}


	// JPQL Query using find employee age
	@Override
	public List<EmployeeResponseDTO> getEmployeesByAge(Integer age) {
		List<Employee> employees = repository.findEmployeesByAge(age);
	    return employees.stream()
	            .map(EmployeeMapper::toResponseDTO)
	            .toList();
		
	}


		
	// native sql query using find employee salary
	@Override
	public List<EmployeeResponseDTO> getEmployeesBySalary(Double salary) {
		List<Employee> employees = repository.findEmployeesBySalary(salary);
		return employees.stream()
				.map(EmployeeMapper :: toResponseDTO)
				.toList();
	}


	// named query using find employee dept
	@Override
	public List<EmployeeResponseDTO> getEmployeesByDepartment(String empDept) {
		List<Employee> employees = repository.findEmployeesByDepartment(empDept);
		return employees.stream()
				.map(EmployeeMapper :: toResponseDTO)
				.toList();
	}
	

	}
