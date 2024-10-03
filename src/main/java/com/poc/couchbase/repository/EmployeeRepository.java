package com.poc.couchbase.repository;

import com.poc.couchbase.models.Employee;
import org.springframework.data.couchbase.repository.*;

import java.util.List;
import java.util.Optional;

@Scope("dev")
@Collection("employee")
public interface EmployeeRepository extends CouchbaseRepository<Employee, String>,
        DynamicProxyable<EmployeeRepository> {
  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND META().id = $id AND deleted = false")
  Optional<Employee> findById(String id);

  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND email = $email AND phoneNumber = $phoneNumber AND deleted =" +
          " false")
  List<Employee> findByEmailAndPhoneNumber(String email, String phoneNumber);

}
