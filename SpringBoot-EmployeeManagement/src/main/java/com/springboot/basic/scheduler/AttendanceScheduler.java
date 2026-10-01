package com.springboot.basic.scheduler;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.basic.Entity.Attendance;
import com.springboot.basic.Entity.Employee;
import com.springboot.basic.repository.AttendanceRepository;
import com.springboot.basic.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AttendanceScheduler {

	
	//Attendance scheduler
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
//	@Scheduled(cron = "0 0 10 * * *")   // every day 10AM
	@Scheduled(fixedRate = 30000)
    public void markAbsentEmployees() {

        LocalDate today = LocalDate.now();

        System.out.println("ATTENDANCE SCHEDULER STARTED");
        System.out.println("==================================================");
        
        System.out.println("Date : " + today);

        // Get all employees
        List<Employee> employees = employeeRepository.findAll();
      
        for (Employee employee : employees) {
            // Check today's attendance
            boolean alreadyPresent = attendanceRepository.findByEmpIdAndAttendanceDate(employee.getEmpId(), today)
                            .isPresent();

            if (!alreadyPresent) {
                Attendance attendance = new Attendance();
                attendance.setEmpId(employee.getEmpId());
                attendance.setAttendanceDate(today);
                attendance.setStatus("ABSENT");
                attendance.setCheckInTime(null);

                attendanceRepository.save(attendance);

                System.out.println("Employee ID : " + employee.getEmpId()
                        + " | Name : " + employee.getEmpName() + " | ABSENT");
            }
        }
        System.out.println("===============================================");
        System.out.println("ATTENDANCE SCHEDULER COMPLETED");
    }
}
	
	
