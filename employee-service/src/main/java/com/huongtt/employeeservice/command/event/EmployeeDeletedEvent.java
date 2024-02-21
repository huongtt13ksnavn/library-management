package com.huongtt.employeeservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDeletedEvent {
    private String id;
}
