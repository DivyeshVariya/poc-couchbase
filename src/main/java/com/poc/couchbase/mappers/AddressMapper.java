package com.poc.couchbase.mappers;

import com.poc.couchbase.dto.request.CreateAddressDto;
import com.poc.couchbase.dto.response.AddressResponseDto;
import com.poc.couchbase.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

  Address toEntity(CreateAddressDto createAddressDto);

  AddressResponseDto toDto(Address address);
}
