package com.poc.couchbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;

@Configuration
@EnableCouchbaseAuditing
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

  // this creates the auditor aware bean that will feed the annotations
  @Bean
  public NaiveAuditorAware testAuditorAware() {
    return new NaiveAuditorAware();
  }

}
