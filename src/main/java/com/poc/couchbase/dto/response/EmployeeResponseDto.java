package com.poc.couchbase.dto.response;

import com.poc.couchbase.models.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeResponseDto extends BaseObject {
  private String id;
  private String firstName;
  private String lastName;
  private Date dob;
  private String email;
  private String phoneNumber;
  private String department;
  private String position;
  private Integer salary;
  private AddressResponseDto address;
}