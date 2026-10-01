package com.springboot.basic.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.basic.Entity.Employee;
import com.springboot.basic.Enums.Role;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

	List<Employee> findByEmpName(String empName);
	List<Employee> findByEmpDept(String empDept);
	List<Employee> findByCity(String city);
	List<Employee> findByState(String state);
	List<Employee> findByAgeGreaterThan(Integer age);
	List<Employee> findBySalaryGreaterThan(Double salary);
	List<Employee> findByEmpNameContainingIgnoreCase(String empName);
	boolean existsByEmpNameAndEmpDept(String empName, String empDept);
	
	// Login User
	Optional<Employee> findByUsernameAndPassword(String username, String password);
	
	List<Employee> findByRole(Role role);
	
	// Pagination and sorting
	Page<Employee> findBySalaryGreaterThan(Double salary, Pageable pageable);

	// Native named queries
	// JPQL Query
	@Query("SELECT e FROM Employee e WHERE e.age > :age")
	List<Employee> findEmployeesByAge(@Param("age") Integer age);
	
	
	// native sql query
	@Query(value = "SELECT * FROM emp_Details_table WHERE salary > :salary", nativeQuery = true)
	List<Employee> findEmployeesBySalary(@Param("salary") Double salary);
	
	
	// named query
	List<Employee> findEmployeesByDepartment(@Param ("empDept") String empDept);
	
}
