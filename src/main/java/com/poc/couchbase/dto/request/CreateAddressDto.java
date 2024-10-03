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
public class CreateAddressDto {
  @NotBlank(message = "AddressLine1 is required")
  private String addressLine1;
  @NotBlank(message = "AddressLine2 is required")
  private String addressLine2;
  @NotBlank(message = "City is required")
  private String city;
  @NotBlank(message = "PinCode is required")
  private String pinCode;
}
