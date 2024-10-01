package com.poc.couchbase.services.impl;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.exceptions.EmployeeNotFound;
import com.poc.couchbase.mappers.EmployeeMapper;
import com.poc.couchbase.models.Employee;
import com.poc.couchbase.repository.EmployeeRepository;
import com.poc.couchbase.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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
    log.trace("Inside createEmployee Method.");
    Employee entity = employeeMapper.toEntity(createEmployeeDto);
    log.trace("After toEntity [{}]",entity);
    Employee savedEmployee = employeeRepository.save(entity);
    log.trace("After save [{}]",savedEmployee);
    EmployeeResponseDto responseDto = employeeMapper.toDto(savedEmployee);
    log.trace("After toDto [{}]",responseDto);
    return responseDto;
  }

  @Override
  public EmployeeResponseDto findEmployeeById(String id) {
    log.trace("Inside findEmployeeById Method.");
    Optional<Employee> entity = employeeRepository.findById(id);
    if (entity.isEmpty()) {
      throw new EmployeeNotFound(String.format("Employee with [%s] id not exist",id));
    }
    EmployeeResponseDto responseDto = employeeMapper.toDto(entity.get());
    log.trace("Response [{}]",responseDto);
    return responseDto;
  }
}
