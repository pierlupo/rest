package com.tp1rest.dto;

import com.tp1rest.entity.Employee;
import org.springframework.stereotype.Component;


@Component
public class EmployeeToEmployeeDTO {

    public EmployeeDto convert(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());

        return employeeDto;
    }
}
