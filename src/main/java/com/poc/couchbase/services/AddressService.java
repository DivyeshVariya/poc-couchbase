package com.poc.couchbase.services;

import com.poc.couchbase.dto.request.CreateAddressDto;
import com.poc.couchbase.dto.response.AddressResponseDto;
import jakarta.validation.Valid;

import java.util.Map;

public interface AddressService {

  AddressResponseDto create(@Valid final CreateAddressDto createAddressDto);

  AddressResponseDto fetchById(final String id);

  Map<String,Object> deleteById(final String id);
}
