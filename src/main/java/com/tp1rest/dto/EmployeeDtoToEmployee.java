package com.tp1rest.dto;

import com.tp1rest.entity.Employee;
import com.tp1rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoToEmployee {


    @Autowired
    EmployeeService employeeService;


    public Employee convert(EmployeeDto employeeDto){



        Employee employee = employeeDto.getId() != null ? employeeService.findById(employeeDto.getId()).get() : new Employee();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setEmail(employeeDto.getEmail());

        return employee;

    }


}
