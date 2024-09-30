package com.poc.couchbase.services;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;

public interface EmployeeService {

  EmployeeResponseDto createEmployee(CreateEmployeeDto employee);
}
