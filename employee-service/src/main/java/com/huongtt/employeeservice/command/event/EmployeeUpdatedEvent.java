package com.huongtt.employeeservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeUpdatedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
