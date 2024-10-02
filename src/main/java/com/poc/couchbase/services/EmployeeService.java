package com.poc.couchbase.services;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.request.UpdateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.filters.EmployeeFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

  EmployeeResponseDto createEmployee(CreateEmployeeDto employee);

  EmployeeResponseDto findEmployeeById(String id);

  Map<String, Object> removeEmployeeById(String id);

  EmployeeResponseDto updateEmployee(UpdateEmployeeDto updateEmployeeDto);

  List<EmployeeResponseDto> getAllEmployees(EmployeeFilter filter);

  Page<EmployeeResponseDto> getAllEmployeesWithPageAndPageSize(EmployeeFilter filter);
}
