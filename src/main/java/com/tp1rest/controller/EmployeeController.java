package com.tp1rest.controller;

import com.tp1rest.dto.EmployeeDto;
import com.tp1rest.dto.EmployeeDtoToEmployee;
import com.tp1rest.dto.EmployeeToEmployeeDTO;
import com.tp1rest.entity.Employee;
import com.tp1rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v2")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDtoToEmployee employeeDtoToEmployee;

    @Autowired
    private EmployeeToEmployeeDTO employeeToEmployeeDTO;

    @PostMapping("/create_employee")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody Employee employee){

        Employee employee1 = employeeService.save(employee);

        return new ResponseEntity<>(employeeToEmployeeDTO.convert(employee1), HttpStatus.OK);

    }

    @DeleteMapping("/delete_employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id){
        if(employeeService.findById(id).isPresent()){
            String message = "Aucun utilisateur avec cet id";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        employeeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/api/v2/all")).build();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable Integer id) {
        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/allEmployees")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> Employees = employeeService.findAll();
        String message = "La liste est vide";
        if (Employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(Employees);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeDto(){
        List <EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employeeService.findAll()){
            employeeDtos.add(employeeToEmployeeDTO.convert(employee));
        }
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmploye(@PathVariable Integer id){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EmployeeDto>(employeeToEmployeeDTO.convert(employee.get()), HttpStatus.OK);

    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id){
        employeeDto.setId(id);
        Employee employee =  employeeService.save(employeeDtoToEmployee.convert(employeeDto));

        return new ResponseEntity<>(employeeToEmployeeDTO.convert(employee),HttpStatus.ACCEPTED);

    }
}
