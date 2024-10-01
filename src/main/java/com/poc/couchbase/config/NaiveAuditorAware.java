package com.poc.couchbase.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class NaiveAuditorAware implements AuditorAware<String> {
  private String auditor = "anonymous";

  @Override
  public Optional<String> getCurrentAuditor() {
    return auditor.describeConstable();
  }

  public void setAuditor(String auditor) {
    this.auditor = auditor;
  }
}
