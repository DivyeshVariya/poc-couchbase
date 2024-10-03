package com.poc.couchbase.dto.response;

import com.poc.couchbase.models.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AddressResponseDto extends BaseObject {
  private String id;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String pinCode;
}
