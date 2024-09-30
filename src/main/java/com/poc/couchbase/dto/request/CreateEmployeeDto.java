package com.poc.couchbase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDto {
  private String firstName;
  private String lastName;
  private String department;
  private String position;
  private String email;
  private String phoneNumber;
  private Integer salary;
}
