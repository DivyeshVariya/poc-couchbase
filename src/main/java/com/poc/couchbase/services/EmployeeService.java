package com.poc.couchbase.services;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;

import java.util.Map;

public interface EmployeeService {

  EmployeeResponseDto createEmployee(CreateEmployeeDto employee);

  EmployeeResponseDto findEmployeeById(String id);

  Map<String, Object> removeEmployeeById(String id);
}
