package com.poc.couchbase.config;

import lombok.Setter;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Setter
public class NaiveAuditorAware implements AuditorAware<String> {
  private String auditor = "anonymous";

  @Override
  public Optional<String> getCurrentAuditor() {
    return auditor.describeConstable();
  }

}
