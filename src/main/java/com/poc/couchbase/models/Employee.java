package com.poc.couchbase.models;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseObject{
  @Id
  private String id;

  private String firstName;
  private String lastName;
  private String dob;

  private String email;
  private String phoneNumber;

  private String department;
  private String position;
  private Integer salary;
}
