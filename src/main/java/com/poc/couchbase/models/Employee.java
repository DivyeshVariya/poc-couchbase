package com.poc.couchbase.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.util.Date;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Document
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseObject {
  @Id
  @GeneratedValue(strategy = UNIQUE)
  private String id;
  @Field
  @NotNull
  private String firstName;
  @Field
  @NotNull
  private String lastName;
  @Field
  @NotNull
  private Date dob;

  @Field
  @NotNull
  private String email;
  @Field
  @NotNull
  private String phoneNumber;

  @Field
  @NotNull
  private String department;
  @Field
  @NotNull
  private String position;
  @Field
  @NotNull
  private Integer salary;
}
