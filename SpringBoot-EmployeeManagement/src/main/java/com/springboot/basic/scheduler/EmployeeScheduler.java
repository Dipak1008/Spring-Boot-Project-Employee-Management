package com.springboot.basic.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.basic.Entity.Employee;
import com.springboot.basic.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeScheduler {

	@Autowired
	EmployeeRepository repository;
	
	// daily employee report at 10 am
//	@Scheduled(cron = "0 0 10 * * *")
	@Scheduled(fixedRate = 10000)
	public void dailyEmployeeReport() {
		long totalEmployees = repository.count();
		
		System.out.println();
		System.out.println("=============================================");
		System.out.println("         DAILY EMPOYEE REPORT     ");
		System.out.println("=============================================");
		System.out.println("Time                  : " + LocalDateTime.now());
		System.out.println("Total Employees       : " + totalEmployees);
		System.out.println("Report Status         : Completed");
		System.out.println("==============================================");
	}
	
	
	// Employee check every 5 minute
	@Scheduled(fixedRate = 30000)
	public void checkEmployee() {
		List<Employee> employees = repository.findAll();
		
		System.out.println();
		System.out.println("=================================================");
		System.out.println("           EMPLOYEE CHECK       ");
		System.out.println("==================================================");
		System.out.println("Time             : " + LocalDateTime.now());
		System.out.println("Employee Found   : " + employees.size());
		  
		for(Employee employee : employees) {
			   System.out.println(
					   "ID : " + employee.getEmpId() + 
					   "| Name : " + employee.getEmpName()	   
					   );
		   }
		System.out.println("===================================================");
	}
	
	
	// database cleanup check every day 11 PM
	@Scheduled(cron = "0 * * * * *")
	public void databaeCleanup() {
		long totalEmployees = repository.count();
		
		System.out.println();
		System.out.println("=================================================");
		System.out.println("          DATABASE CLEANUP CHECK        ");
	    System.out.println("=================================================");
	    System.out.println("Time             : " + LocalDateTime.now());
        System.out.println("Employee Records : " + totalEmployees);
        System.out.println("Cleanup Status   : Completed");
        System.out.println("=================================================");
    }
	
	}
	
