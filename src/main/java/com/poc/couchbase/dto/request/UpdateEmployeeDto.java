package com.poc.couchbase.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
  @NotBlank(message = "Id is required")
  private String id;
  private String firstName;
  private String lastName;
}
