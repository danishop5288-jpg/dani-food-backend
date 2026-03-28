package com.dani.danifood.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class EmployeeLoginDTO implements Serializable {
    private String userName;
    private String password;
}
