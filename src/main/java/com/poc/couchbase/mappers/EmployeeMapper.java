package com.poc.couchbase.mappers;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.models.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

  Employee toEntity(CreateEmployeeDto createEmployeeDto);

  EmployeeResponseDto toDto(Employee employee);
}
