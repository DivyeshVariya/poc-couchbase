package com.poc.couchbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCouchbaseAuditing
@EnableTransactionManagement
public class CouchbaseServerConfig extends AbstractCouchbaseConfiguration {
  @Override
  public String getConnectionString() {
    return "couchbase://localhost";
  }

  @Override
  public String getUserName() {
    return "root";
  }

  @Override
  public String getPassword() {
    return "roooot";
  }

  @Override
  public String getBucketName() {
    return "poc-couchbase";
  }

  // optionally specify the scope in the Configuration
  @Override
  protected String getScopeName() {
    return "dev";
  }

  // this creates the auditor aware bean that will feed the annotations
  @Bean
  public NaiveAuditorAware testAuditorAware() {
    return new NaiveAuditorAware();
  }
}
