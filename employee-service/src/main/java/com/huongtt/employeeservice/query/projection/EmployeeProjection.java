package com.huongtt.employeeservice.query.projection;

import com.huongtt.employeeservice.command.data.Employee;
import com.huongtt.employeeservice.command.data.EmployeeRepository;
import com.huongtt.employeeservice.query.model.EmployeeResponseModel;
import com.huongtt.employeeservice.query.query.GetAllEmployeeQuery;
import com.huongtt.employeeservice.query.query.GetEmployeeDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    private EmployeeResponseModel handle(GetEmployeeDetailQuery getEmployeeDetailQuery) throws Exception {
        EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
        Employee employee = employeeRepository.findById(getEmployeeDetailQuery.getId()).orElseThrow(
                () -> new Exception("Not found employee id")
        );
        BeanUtils.copyProperties(employee, employeeResponseModel);
        return employeeResponseModel;
    }

    @QueryHandler
    private List<EmployeeResponseModel> handle(GetAllEmployeeQuery getAllEmployeeQuery) {
        List<EmployeeResponseModel> employeeResponseModels = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(item -> {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(item, employeeResponseModel);
        });
        return employeeResponseModels;
    }
}
