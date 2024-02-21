package com.huongtt.employeeservice.command.event;

import com.huongtt.employeeservice.command.data.Employee;
import com.huongtt.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventsHandler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreatedEvent employeeCreatedEvent) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeCreatedEvent, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent employeeUpdatedEvent) throws Exception {
        Employee employee = employeeRepository.findById(employeeUpdatedEvent.getId()).orElseThrow(
                () -> new Exception("Not found employee id"));
        BeanUtils.copyProperties(employeeUpdatedEvent, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeDeletedEvent employeeDeletedEvent){
        employeeRepository.deleteById(employeeDeletedEvent.getId());
    }
}
