package com.tp1rest.service;


import com.tp1rest.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    Employee save(Employee employee);

    void deleteById(Integer id);

}
