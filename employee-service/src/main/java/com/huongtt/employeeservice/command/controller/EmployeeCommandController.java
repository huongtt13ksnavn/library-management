package com.huongtt.employeeservice.command.controller;

import com.huongtt.employeeservice.command.command.CreateEmployeeCommand;
import com.huongtt.employeeservice.command.command.DeleteEmployeeCommand;
import com.huongtt.employeeservice.command.command.UpdateEmployeeCommand;
import com.huongtt.employeeservice.command.model.EmployeeRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        CreateEmployeeCommand createEmployeeCommand = new CreateEmployeeCommand(UUID.randomUUID().toString(), employeeRequestModel.getFirstName(), employeeRequestModel.getLastName()
                , employeeRequestModel.getKin(), employeeRequestModel.getIsDisciplined());
        commandGateway.sendAndWait(createEmployeeCommand);
        return "added employee";
    }

    @PutMapping
    public String updateEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        UpdateEmployeeCommand updateEmployeeCommand = new UpdateEmployeeCommand(UUID.randomUUID().toString(), employeeRequestModel.getFirstName(), employeeRequestModel.getLastName()
                , employeeRequestModel.getKin(), employeeRequestModel.getIsDisciplined());
        commandGateway.sendAndWait(updateEmployeeCommand);
        return "updated employee";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable String id) {
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(id);
        commandGateway.sendAndWait(deleteEmployeeCommand);
        return "deleted employee";
    }
}
