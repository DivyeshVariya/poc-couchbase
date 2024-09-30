package com.poc.couchbase.repository;

import com.poc.couchbase.models.Employee;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CouchbaseRepository<Employee,String> {
}
