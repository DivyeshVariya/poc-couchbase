package com.poc.couchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;

@EnableCouchbaseAuditing
@SpringBootApplication
public class CouchbaseApplication {
  public static void main(String[] args) {
    SpringApplication.run(CouchbaseApplication.class, args);
  }
}