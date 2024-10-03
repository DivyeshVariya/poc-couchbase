package com.poc.couchbase.mappers;

import com.poc.couchbase.dto.request.CreateEmployeeDto;
import com.poc.couchbase.dto.request.UpdateEmployeeDto;
import com.poc.couchbase.dto.response.AddressResponseDto;
import com.poc.couchbase.dto.response.EmployeeResponseDto;
import com.poc.couchbase.models.Employee;
import com.poc.couchbase.services.AddressService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class EmployeeMapper {
  @Autowired
  private AddressService addressService;

  public abstract Employee toEntity(CreateEmployeeDto createEmployeeDto);

  @Mapping(target = "address", source = "addressId", qualifiedByName = "fetchAddressById")
  public abstract EmployeeResponseDto toDto(Employee employee);

  public abstract Employee updateEntity(UpdateEmployeeDto updateEmployeeDto, @MappingTarget Employee employee);

  @Named("fetchAddressById")
  public AddressResponseDto fetchAddressById(String addressId) {
    // Implementation to fetch address by ID using Couchbase or any other data source
    return addressService.fetchById(addressId);
  }
}
