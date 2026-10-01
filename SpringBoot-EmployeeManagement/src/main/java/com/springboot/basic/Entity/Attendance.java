package com.springboot.basic.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

	// Scheduler entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendanceId;
	private Integer empId;
	private LocalDate attendanceDate;
	private String status;
	private LocalTime checkInTime;
	
}
