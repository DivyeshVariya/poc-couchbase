package com.poc.couchbase.controller;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}

  @PostMapping("")
  public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
    EmployeeResponseDto responseDto = employeeService.createEmployee(createEmployeeDto);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseDto);
  }
}
