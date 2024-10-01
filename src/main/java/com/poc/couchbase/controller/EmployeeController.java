package com.poc.couchbase.controller;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.request.UpdateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.dto.response.Response;
import com.poc.couchbase.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
  private final static String DATA = "data";
  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}

  @PostMapping("/create")
  public ResponseEntity<Response> createEmployee(@Valid @RequestBody final CreateEmployeeDto createEmployeeDto) {
    EmployeeResponseDto responseDto = employeeService.createEmployee(createEmployeeDto);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(Response
                    .builder()
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .message("Employee created.")
                    .data(Map.of(DATA, responseDto))
                    .build());
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<Response> findById(@PathVariable(name = "id") final String id) {
    EmployeeResponseDto responseDto = employeeService.findEmployeeById(id);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response
                    .builder()
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .message("Employee found.")
                    .data(Map.of(DATA, responseDto))
                    .build());
  }

  @PutMapping("/update")
  public ResponseEntity<Response> update(@Valid @RequestBody final UpdateEmployeeDto updateEmployeeDto) {
    EmployeeResponseDto responseDto = employeeService.updateEmployee(updateEmployeeDto);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response
                    .builder()
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .message("Employee updated.")
                    .data(Map.of(DATA, responseDto))
                    .build());
  }

  @DeleteMapping("/removeById/{id}")
  public ResponseEntity<Response> removeById(@PathVariable(name = "id") final String id) {
    Map<String,Object> responseDto = employeeService.removeEmployeeById(id);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response
                    .builder()
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .message("Employee removed.")
                    .data(Map.of(DATA, responseDto))
                    .build());
  }
}
