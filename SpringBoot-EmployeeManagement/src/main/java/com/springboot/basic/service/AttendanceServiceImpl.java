package com.springboot.basic.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.basic.Entity.Attendance;
import com.springboot.basic.Entity.Employee;
import com.springboot.basic.dto.AttendanceResponseDTO;
import com.springboot.basic.repository.AttendanceRepository;
import com.springboot.basic.repository.EmployeeRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public AttendanceResponseDTO checkIn(Integer empId) {
		
		 // Check employee exists
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        LocalDate today = LocalDate.now();


     // Find today's attendance record
      Optional<Attendance> existingAttendance = attendanceRepository
      .findByEmpIdAndAttendanceDate(empId, today); 
      
      Attendance attendance; 
      if (existingAttendance.isPresent()) { 
    	  
    	  attendance = existingAttendance.get();
    	  
    	  // If check-in time already exists
    	  if (attendance.getCheckInTime() != null) { 
    		  throw new RuntimeException("Employee already checked in today"); 
          } 
    	  
    	  // Existing record is ABSENT, so change it to PRESENT 
    	  attendance.setStatus("PRESENT"); 
    	  attendance.setCheckInTime(LocalTime.now());
    	  
    	  } else { 
    		  
    		  // No record exists, create new attendance
    		  attendance = new Attendance(); 
    		  attendance.setEmpId(employee.getEmpId()); 
    		  attendance.setAttendanceDate(today); 
    		  attendance.setStatus("PRESENT"); 
    		  attendance.setCheckInTime(LocalTime.now()); 
    		  }
      // Save attendance 
       Attendance savedAttendance = attendanceRepository.save(attendance); 
      
      // Create response 
      AttendanceResponseDTO response = new AttendanceResponseDTO(); 
      
      response.setAttendanceId(savedAttendance.getAttendanceId()); 
      response.setEmpId(savedAttendance.getEmpId()); 
      response.setAttendanceDate(savedAttendance.getAttendanceDate()); 
      response.setStatus(savedAttendance.getStatus());
      response.setCheckInTime(savedAttendance.getCheckInTime());
      
      return response; 
      } 
	}
    	  
    	  
      