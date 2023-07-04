package com.tp1rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;
}
