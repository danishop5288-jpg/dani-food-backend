package com.dani.danifood.controller;

import com.dani.danifood.config.JwtProperties;
import com.dani.danifood.constant.JwtClaimsConstant;
import com.dani.danifood.dto.EmployeeLoginDTO;
import com.dani.danifood.entity.Employee;
import com.dani.danifood.result.Result;
import com.dani.danifood.service.EmployeeService;
import com.dani.danifood.utils.JwtUtil;
import com.dani.danifood.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    JwtProperties jwtProperties;

    @PostMapping("/api/login")
    public Result<EmployeeLoginVO>login(@RequestBody EmployeeLoginDTO  employeeLoginDTO) {
        log.info("Employee login: {}", employeeLoginDTO);
      Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());//成功了才会生成令牌
        log.info("secretKey: {}", jwtProperties.getSecretKey());
        log.info("ttl: {}", jwtProperties.getTtl());
        String token = JwtUtil.createJWT(//creat token
                jwtProperties.getSecretKey(),//拿密匙-挺像房卡的😂
                jwtProperties.getTtl(),//拿有效期
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())//Map employee.id to the VO's id fields.
                .userName(employee.getUserName())
                .name(employee.getName())
                .token(token)
                .build();
        return Result.success(employeeLoginVO);

    }
}
