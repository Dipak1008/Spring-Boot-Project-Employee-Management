package com.springboot.basic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponseDTO {

	// Scheduler DTO
	private Integer attendanceId;
	private Integer empId;
	private LocalDate attendanceDate;
	private String status;
	private LocalTime checkInTime;
}
