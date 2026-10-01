package com.springboot.basic.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.basic.Entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	
	// for scheduler repos
	Optional<Attendance> findByEmpIdAndAttendanceDate(Integer empId, LocalDate attendanceDate);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);
}