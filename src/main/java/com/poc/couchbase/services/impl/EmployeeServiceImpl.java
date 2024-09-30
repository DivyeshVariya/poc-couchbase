package com.poc.couchbase.services.impl;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.mappers.EmployeeMapper;
import com.poc.couchbase.models.Employee;
import com.poc.couchbase.repository.EmployeeRepository;
import com.poc.couchbase.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
  }

  @Override
  public EmployeeResponseDto createEmployee(CreateEmployeeDto createEmployeeDto) {
    Employee entity = employeeMapper.toEntity(createEmployeeDto);
    Employee savedEmployee = employeeRepository.save(entity);
    return employeeMapper.toDto(savedEmployee);
  }
}
