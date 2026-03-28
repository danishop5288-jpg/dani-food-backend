package com.dani.danifood.service;

import com.dani.danifood.dto.EmployeeLoginDTO;
import com.dani.danifood.entity.Employee;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
