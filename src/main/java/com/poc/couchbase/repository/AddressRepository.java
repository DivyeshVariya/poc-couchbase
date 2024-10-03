package com.poc.couchbase.repository;

import com.poc.couchbase.models.Address;
import org.springframework.data.couchbase.repository.*;

import java.util.Optional;
@Scope("dev")
@Collection("address")
public interface AddressRepository extends CouchbaseRepository<Address,String>, DynamicProxyable<AddressRepository> {
  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND META().id = $id AND deleted = false")
  Optional<Address> findById(String id);
}
