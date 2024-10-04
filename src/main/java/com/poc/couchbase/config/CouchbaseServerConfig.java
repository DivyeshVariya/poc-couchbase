package com.poc.couchbase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCouchbaseAuditing
@EnableTransactionManagement
public class CouchbaseServerConfig extends AbstractCouchbaseConfiguration {
  @Value("${couchbase.connection-string}")
  private String connectionString;
  @Value("${couchbase.username}")
  private String username;
  @Value("${couchbase.password}")
  private String password;
  @Value("${couchbase.bucket-name}")
  private String bucketName;
  @Value("${couchbase.scope}")
  private String scope;

  @Override
  public String getConnectionString() {
    return connectionString;
  }

  @Override
  public String getUserName() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getBucketName() {
    return bucketName;
  }

  // optionally specify the scope in the Configuration
  @Override
  protected String getScopeName() {
    return scope;
  }

  // this creates the auditor aware bean that will feed the annotations
  @Bean
  public NaiveAuditorAware testAuditorAware() {
    return new NaiveAuditorAware();
  }
}
