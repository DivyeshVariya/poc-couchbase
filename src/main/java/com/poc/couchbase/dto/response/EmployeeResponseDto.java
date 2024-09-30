package com.poc.couchbase.dto.response;

import com.poc.couchbase.models.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto extends BaseObject {
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
