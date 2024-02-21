package com.huongtt.employeeservice.query.controller;

import com.huongtt.employeeservice.query.model.EmployeeResponseModel;
import com.huongtt.employeeservice.query.query.GetAllEmployeeQuery;
import com.huongtt.employeeservice.query.query.GetEmployeeDetailQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public EmployeeResponseModel getEmployeeDetail(@PathVariable String id) {
        GetEmployeeDetailQuery getEmployeeDetailQuery = new GetEmployeeDetailQuery();
        getEmployeeDetailQuery.setId(id);
        return queryGateway.query(getEmployeeDetailQuery, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping
    public List<EmployeeResponseModel> getEmployees() {
        GetAllEmployeeQuery getAllEmployeeQuery = new GetAllEmployeeQuery();
        return queryGateway.query(getAllEmployeeQuery, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }
}
