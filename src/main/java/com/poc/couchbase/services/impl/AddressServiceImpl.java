package com.poc.couchbase.services.impl;

import com.poc.couchbase.dto.request.CreateAddressDto;
import com.poc.couchbase.dto.response.AddressResponseDto;
import com.poc.couchbase.exceptions.AddressNotFoundException;
import com.poc.couchbase.mappers.AddressMapper;
import com.poc.couchbase.models.Address;
import com.poc.couchbase.repository.AddressRepository;
import com.poc.couchbase.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static com.poc.couchbase.constants.Constants.DELETED;
import static com.poc.couchbase.constants.Constants.ID;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
  @Value("${couchbase.scope}")
  private String scope;

  private final AddressRepository addressRepository;
  private final AddressMapper addressMapper;

  @Autowired
  public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
    this.addressRepository = addressRepository;
    this.addressMapper = addressMapper;
  }

  @Override
  @Transactional
  public AddressResponseDto create(final CreateAddressDto createAddressDto) {
    log.trace("Inside createAddress Method.");

    log.trace("Input Address: [{}]", createAddressDto);
    Address entity = addressMapper.toEntity(createAddressDto);
    log.trace("After Mapping to Entity: [{}]", entity);
    Address saved = addressRepository.withScope(scope).save(entity);
    log.trace("After Save: [{}]", saved);
    return addressMapper.toDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public AddressResponseDto fetchById(final String id) {
    log.trace("Inside fetchById Method.");
    Optional<Address> address = addressRepository.withScope(scope).findById(id);
    if (address.isEmpty()) {
      throw new AddressNotFoundException("Address not found with id: " + id);
    }
    return addressMapper.toDto(address.get());
  }

  @Override
  @Transactional
  public Map<String, Object> deleteById(final String id) {
    log.trace("Inside deleteById Method.");
    Optional<Address> address = addressRepository.withScope(scope).findById(id);
    if (address.isEmpty()) {
      throw new AddressNotFoundException("Address not found with id: " + id);
    }
    address
            .get()
            .setDeleted(Boolean.TRUE);
    Address saved = addressRepository.save(address.get());
    log.trace("After saved [{}]", address);
    return Map.of(ID, saved.getId(), DELETED, saved.isDeleted());
  }
}
