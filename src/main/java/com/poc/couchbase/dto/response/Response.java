package com.poc.couchbase.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
  private HttpStatus status;
  private int statusCode;
  private String message;
  private Map<?, ?> data;
}
