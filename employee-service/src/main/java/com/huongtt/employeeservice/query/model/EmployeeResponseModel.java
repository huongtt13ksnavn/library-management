package com.huongtt.employeeservice.query.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseModel {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
