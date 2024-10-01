package com.poc.couchbase.repository;

import com.poc.couchbase.models.Employee;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface EmployeeRepository extends CouchbaseRepository<Employee,String> {
}
