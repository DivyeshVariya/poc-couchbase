package com.poc.couchbase.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Scope("dev")
@Collection("address")
@EqualsAndHashCode(callSuper = true)
@Document
@Data
@SuperBuilder
@NoArgsConstructor
public class Address extends BaseObject{

  @Id
  @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
  private String id;
  @NotNull
  @Field
  private String addressLine1;
  @NotNull
  @Field
  private String addressLine2;
  @NotNull
  @Field
  private String city;
  @NotNull
  @Field
  private String pinCode;
}
