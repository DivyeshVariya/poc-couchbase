package com.poc.couchbase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDto {
  private String firstName;
  private String lastName;
  private Date dob;
  private String department;
  private String position;
  private String email;
  private String phoneNumber;
  private Integer salary;
}
