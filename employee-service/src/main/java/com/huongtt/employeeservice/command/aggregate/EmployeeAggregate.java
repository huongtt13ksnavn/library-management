package com.huongtt.employeeservice.command.aggregate;

import com.huongtt.employeeservice.command.command.CreateEmployeeCommand;
import com.huongtt.employeeservice.command.command.DeleteEmployeeCommand;
import com.huongtt.employeeservice.command.command.UpdateEmployeeCommand;
import com.huongtt.employeeservice.command.event.EmployeeCreatedEvent;
import com.huongtt.employeeservice.command.event.EmployeeDeletedEvent;
import com.huongtt.employeeservice.command.event.EmployeeUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Getter
@Setter
@NoArgsConstructor
public class EmployeeAggregate {
    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand) {
        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(createEmployeeCommand, employeeCreatedEvent);
        AggregateLifecycle.apply(employeeCreatedEvent);
    }
    @CommandHandler
    public void handle(UpdateEmployeeCommand updateEmployeeCommand) {
        EmployeeUpdatedEvent employeeUpdatedEvent = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(updateEmployeeCommand, employeeUpdatedEvent);
        AggregateLifecycle.apply(employeeUpdatedEvent);
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand deleteEmployeeCommand) {
        EmployeeDeletedEvent employeeDeletedEvent = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(deleteEmployeeCommand, employeeDeletedEvent);
        AggregateLifecycle.apply(employeeDeletedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent employeeCreatedEvent) {
        this.id = employeeCreatedEvent.getId();
        this.firstName = employeeCreatedEvent.getFirstName();
        this.lastName = employeeCreatedEvent.getLastName();
        this.kin = employeeCreatedEvent.getKin();
        this.isDisciplined = employeeCreatedEvent.getIsDisciplined();
    }

    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent employeeUpdatedEvent) {
        this.id = employeeUpdatedEvent.getId();
        this.firstName = employeeUpdatedEvent.getFirstName();
        this.lastName = employeeUpdatedEvent.getLastName();
        this.kin = employeeUpdatedEvent.getKin();
        this.isDisciplined = employeeUpdatedEvent.getIsDisciplined();
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent employeeDeletedEvent) {
        this.id = employeeDeletedEvent.getId();
    }
}
