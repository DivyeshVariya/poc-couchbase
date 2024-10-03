package com.poc.couchbase.services.impl;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.request.UpdateEmployeeDto;
import com.poc.couchbase.dto.response.AddressResponseDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.exceptions.EmployeeAlreadyCreatedException;
import com.poc.couchbase.exceptions.EmployeeNotFoundException;
import com.poc.couchbase.filters.EmployeeFilter;
import com.poc.couchbase.filters.common.CouchbaseExecutorService;
import com.poc.couchbase.mappers.EmployeeMapper;
import com.poc.couchbase.models.Employee;
import com.poc.couchbase.repository.EmployeeRepository;
import com.poc.couchbase.services.AddressService;
import com.poc.couchbase.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.poc.couchbase.constants.Constants.DELETED;
import static com.poc.couchbase.constants.Constants.ID;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;
  private final AddressService addressService;
  private final CouchbaseExecutorService couchbaseExecutorService;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                             AddressService addressService,
                             CouchbaseExecutorService couchbaseExecutorService) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
    this.addressService = addressService;
    this.couchbaseExecutorService = couchbaseExecutorService;
  }

  @Override
  @Transactional
  public EmployeeResponseDto createEmployee(CreateEmployeeDto createEmployeeDto) {
    log.trace("Inside createEmployee Method.");
    log.trace("Check email and phoneNumber present.");
    List<Employee> byEmailAndPhoneNumberExits =
            employeeRepository.findByEmailAndPhoneNumber(createEmployeeDto.getEmail(),
                    createEmployeeDto.getPhoneNumber());
    log.trace("Employee exists with email and phone [{}]", byEmailAndPhoneNumberExits);
    if (!byEmailAndPhoneNumberExits.isEmpty()) {
      throw new EmployeeAlreadyCreatedException(String.format("Email [%s] and Phone number [%s] already exists",
              createEmployeeDto.getEmail(), createEmployeeDto.getPhoneNumber()));
    }
    AddressResponseDto addressResponseDto = addressService.create(createEmployeeDto.getAddress());
    log.trace("Address created [{}]", addressResponseDto);
    Employee entity = employeeMapper.toEntity(createEmployeeDto);
    entity.setAddressId(addressResponseDto.getId());
    log.trace("After toEntity [{}]", entity);
    Employee savedEmployee = employeeRepository.withScope("dev").save(entity);
    log.trace("After save [{}]", savedEmployee);
    EmployeeResponseDto responseDto = employeeMapper.toDto(savedEmployee);
    log.trace("After toDto [{}]", responseDto);
    return responseDto;
  }

  @Override
  @Transactional
  public EmployeeResponseDto findEmployeeById(String id) {
    log.trace("Inside findEmployeeById Method.");
    Optional<Employee> entity = employeeRepository.withScope("dev").findById(id);
    if (entity.isEmpty()) {
      throw new EmployeeNotFoundException(String.format("Employee with [%s] id not exist", id));
    }
    EmployeeResponseDto responseDto = employeeMapper.toDto(entity.get());
    log.trace("Response [{}]", responseDto);
    return responseDto;
  }

  @Override
  @Transactional
  public Map<String, Object> removeEmployeeById(String id) {
    log.trace("Inside removeEmployeeById Method.");
    Optional<Employee> entity = employeeRepository.withScope("dev").findById(id);
    if (entity.isEmpty()) {
      throw new EmployeeNotFoundException(String.format("Employee with [%s] id not exist", id));
    }
    Map<String, Object> deleted = addressService.deleteById(entity
            .get()
            .getAddressId());
    log.trace("Deleted address [{}]", deleted);
    entity
            .get()
            .setDeleted(Boolean.TRUE);
    Employee saved = employeeRepository.save(entity.get());
    log.trace("After saved [{}]", saved);
    return Map.of(ID, id, DELETED, saved.isDeleted());
  }

  @Override
  @Transactional
  public EmployeeResponseDto updateEmployee(UpdateEmployeeDto updateEmployeeDto) {
    log.trace("Inside updateEmployee Method.");
    Optional<Employee> entity = employeeRepository.withScope("dev").findById(updateEmployeeDto.getId());
    if (entity.isEmpty()) {
      throw new EmployeeNotFoundException(String.format("Employee with [%s] id not exist", updateEmployeeDto.getId()));
    }
    log.trace("Updating employee entity.");
    Employee updateEntity = employeeMapper.updateEntity(updateEmployeeDto, entity.get());
    log.trace("Updated employee entity [{}]", updateEntity);
    return employeeMapper.toDto(updateEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EmployeeResponseDto> getAllEmployees(EmployeeFilter filter) {
    log.trace("Inside getAllEmployees Method.");

    log.trace("Query [{}]", filter.toQuery());
    List<Employee> getAllEmployees = couchbaseExecutorService.find("getAllEmployees", filter.toQuery(), Employee.class);

    log.trace("[{}] employees found", getAllEmployees.size());
    return getAllEmployees
            .stream()
            .map(employeeMapper::toDto)
            .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<EmployeeResponseDto> getAllEmployeesWithPageAndPageSize(EmployeeFilter filter) {
    log.trace("Inside getAllEmployeesWithPageAndPageSize Method.");

    log.trace("Query [{}]", filter.toQuery());
    Page<Employee> getAllEmployees =
            couchbaseExecutorService.findByPage("getAllEmployeesWithPageAndPageSize", filter.toQuery(),
                    Employee.class, filter.getPageSize(), filter.getPage());

    log.trace("[{}] employees found", getAllEmployees.getTotalElements());

    List<EmployeeResponseDto> employeeResponseDtoList = getAllEmployees
            .stream()
            .map(employeeMapper::toDto)
            .toList();

    return new PageImpl<>(employeeResponseDtoList, getAllEmployees.getPageable(), getAllEmployees.getTotalElements());
  }
}
