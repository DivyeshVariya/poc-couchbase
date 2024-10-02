package com.poc.couchbase.controller;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.request.UpdateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.dto.response.Response;
import com.poc.couchbase.filters.EmployeeFilter;
import com.poc.couchbase.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.poc.couchbase.constants.Constants.COUNT;
import static com.poc.couchbase.constants.Constants.DATA;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

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
    Map<String, Object> responseDto = employeeService.removeEmployeeById(id);
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

  @PostMapping("/filter")
  public ResponseEntity<Response> getAllEmployeesWithFilter(@RequestBody EmployeeFilter filter) {
    List<EmployeeResponseDto> responseDto = employeeService.getAllEmployees(filter);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response
                    .builder()
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .message("Employee found.")
                    .data(Map.of(DATA, responseDto, COUNT, responseDto.size()))
                    .build());
  }

  @PostMapping("/filter/all")
  public ResponseEntity<Response> getAllEmployeesWithPageAndPageSize(@RequestBody EmployeeFilter filter) {
    Page<EmployeeResponseDto> responseDto = employeeService.getAllEmployeesWithPageAndPageSize(filter);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Response
                    .builder()
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .message("Employee found.")
                    .data(Map.of(DATA, responseDto, COUNT, responseDto.getTotalElements()))
                    .build());
  }
}
