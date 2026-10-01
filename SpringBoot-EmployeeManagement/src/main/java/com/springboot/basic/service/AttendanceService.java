package com.springboot.basic.service;

import com.springboot.basic.dto.AttendanceResponseDTO;

public interface AttendanceService {

    AttendanceResponseDTO checkIn(Integer empId);

}
