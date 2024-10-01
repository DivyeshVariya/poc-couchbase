package com.poc.couchbase.repository;

import com.poc.couchbase.models.Employee;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends CouchbaseRepository<Employee,String> {
  @Query("#{#n1ql.selectEntity} WHERE META().id = $id AND deleted = false")
  Optional<Employee> findById(String id);
}
