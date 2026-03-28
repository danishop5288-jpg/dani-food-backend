package com.dani.danifood.service.ServiceImpl;

import com.dani.danifood.constant.MessageConstant;
import com.dani.danifood.dto.EmployeeLoginDTO;
import com.dani.danifood.entity.Employee;
import com.dani.danifood.exception.BaseException;
import com.dani.danifood.exception.PasswordErrorException;
import com.dani.danifood.exception.UsernameNotExistException;
import com.dani.danifood.repository.EmployeeRepository;
import com.dani.danifood.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j//@Slf4j is a Lombok annotation that auto-generates a logger field,
// allowing you to use log.info() directly.
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;//encrypt password in DB

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUserName();//1️⃣
        String rawPassword = employeeLoginDTO.getPassword();//2️⃣//plain text password from frontend(employee input)
        log.info("Login attempt: {}", employeeLoginDTO.getUserName());
        Employee employee = employeeRepository.findByUserName(username);
        if (employee == null) {//1️⃣employee vs. employeeDTO(username)
            throw new UsernameNotExistException(MessageConstant.USERNAME_NOT_EXIST);
        }
        if (!passwordEncoder.matches(rawPassword, employee.getPassword())) {//2️⃣
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }//password: The raw password entered by the Employee.
        // employee.getPassword(): The encoded hash stored in your database.
        /*
        we can't use equals(),because it compares two strings literally.
        we are comparing plain text (from the Employee) with a hashed string (from the DB).
        They will never match.
        if (!employee.getPassword().equals(password)) {//2️⃣
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
       */
        log.info("Employee found: {}", employee);
        return employee;
    }
}
