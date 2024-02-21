package com.huongtt.employeeservice.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestModel {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
