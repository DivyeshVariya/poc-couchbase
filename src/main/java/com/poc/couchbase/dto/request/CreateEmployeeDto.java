package com.poc.couchbase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotBlank(message = "Email must not be empty")
  private String email;
  @NotBlank(message = "Phone number must not be empty")
  private String phoneNumber;
  private Integer salary;
  @NotNull(message = "Address must not be null")
  private CreateAddressDto address;
}
