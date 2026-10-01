package com.springboot.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.basic.dto.AttendanceResponseDTO;
import com.springboot.basic.service.AttendanceServiceImpl;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	AttendanceServiceImpl attendanceServiceImpl;
	
	@PostMapping("/check-in/{empId}")
	public ResponseEntity<AttendanceResponseDTO> checkIn( @PathVariable Integer empId) {
		AttendanceResponseDTO response = attendanceServiceImpl.checkIn(empId);
		return ResponseEntity.ok(response);
		
	}

}
