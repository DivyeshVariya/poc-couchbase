package com.poc.couchbase.filters.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CouchbaseExecutorService {
  private final CouchbaseTemplate couchbaseTemplate;

  @Autowired
  public CouchbaseExecutorService(CouchbaseTemplate couchbaseTemplate) {
    this.couchbaseTemplate = couchbaseTemplate;
  }

  public <T> List<T> find(String source, Query query, Class<T> t) {
    log.trace("Inside find method.");
    log.trace("Source: [{}] , Query: [{}], Class: [{}]", source, query.toString(), t.getSimpleName());
    source = setSourceIfNull(source);
    long startTime = System.currentTimeMillis();
    List<T> content = couchbaseTemplate
            .findByQuery(t)
            .matching(query)
            .all();
    calculateResponseTime(startTime, source);
    return content;
  }

//  public <T> List<T> find(String source, Query query, Class<T> t, String... fieldsToInclude) {
//    log.trace("Inside find method.");
//    log.trace("Source: [{}] , Query: [{}], Class: [{}]", source, query, t.getSimpleName());
//    source = setSourceIfNull(source);
//    long startTime = System.currentTimeMillis();
//
//    // Add projection to the query if fields to include are specified
//    if (fieldsToInclude!=null && fieldsToInclude.length > 0) {
//      Field fields = query.fields();
//      for (String field : fieldsToInclude) {
//        fields.include(field);
//      }
//    }
//
//    List<T> content = couchbaseTemplate.find(query, t);
//    calculateResponseTime(startTime, source);
//    return content;
//  }

//  public <T> T findOne(String source, Query query, Class<T> t) {
//    log.trace("Inside findOne method.");
//    log.trace("Source: [{}] , Query: [{}], Class: [{}] ", source, query, t.getSimpleName());
//    source = setSourceIfNull(source);
//    long startTime = System.currentTimeMillis();
//    T result = couchbaseTemplate.findOne(query, t);
//    calculateResponseTime(startTime, source);
//    return result;
//  }

  public <T> Long count(String source, Query query, Class<T> t) {
    log.trace("Inside count method.");
    log.trace("Source: [{}] , Query: [{}], Class: [{}] ", source, query, t.getSimpleName());
    source = setSourceIfNull(source);
    long startTime = System.currentTimeMillis();
    long result = couchbaseTemplate.count(query, t);
    calculateResponseTime(startTime, source);
    return result;
  }

  public <T> Page<T> findByPage(String source, Query query, Class<T> t, int pageSize, int page) {
    log.trace("Inside findByPage method.");
    log.trace(
            "Source: [{}] , Query: [{}], Class: [{}], PageSize : [{}], Page : [{}]",
            source,
            query,
            t.getSimpleName(),
            pageSize,
            page);
    source = setSourceIfNull(source);
    long totalElements = couchbaseTemplate.count(query, t);
    if (pageSize <= 0) {
      pageSize = 100;
    }

    Pageable pageable = PageRequest.of(page, pageSize);
    query.with(pageable);
    long startTime = System.currentTimeMillis();
    List<T> content = couchbaseTemplate
            .findByQuery(t)
            .matching(query)
            .all();
    calculateResponseTime(startTime, source);
    return PageableExecutionUtils.getPage(content, pageable, () -> totalElements);
  }

//  public <T> Page<T> findByPage(
//          String source, Query query, Class<T> t, int pageSize, int page, String... fieldsToInclude) {
//    log.trace("Inside findByPage method.");
//    log.trace(
//            "Source: [{}] , Query: [{}], Class: [{}], PageSize : [{}], Page : [{}]",
//            source,
//            query,
//            t.getSimpleName(),
//            pageSize,
//            page);
//    source = setSourceIfNull(source);
//    long totalElements = couchbaseTemplate.count(query, t);
//    if (pageSize <= 0) {
//      pageSize = 100;
//    }
//
//    Pageable pageable = PageRequest.of(page, pageSize);
//    query.with(pageable);
//
//    // Add projection to the query if fields to include are specified
//    if (fieldsToInclude!=null && fieldsToInclude.length > 0) {
//      Field fields = query.fields();
//      for (String field : fieldsToInclude) {
//        fields.include(field);
//      }
//    }
//
//    long startTime = System.currentTimeMillis();
//    List<T> content = couchbaseTemplate.find(query, t);
//    calculateResponseTime(startTime, source);
//    return PageableExecutionUtils.getPage(content, pageable, () -> totalElements);
//  }

  private String setSourceIfNull(String source) {
    return source.isEmpty() ? "DEFAULT" : source;
  }

  private void calculateResponseTime(long startTime, String source) {
    long responseTime = System.currentTimeMillis() - startTime;
    if (responseTime < 100) {
      log.info("Super fast response time: {} ms. MongoDB Query: [{}]", responseTime, source);
    } else if (responseTime < 300) {
      log.info("Very fast response time: {} ms. MongoDB Query: [{}]", responseTime, source);
    } else if (responseTime < 500) {
      log.warn("Moderate response time: {} ms. MongoDB Query: [{}]", responseTime, source);
    } else if (responseTime < 1000) {
      log.warn("High response time: {} ms. MongoDB Query: [{}]", responseTime, source);
    } else {
      log.warn("Very high response time: {} ms. MongoDB Query: [{}]", responseTime, source);
    }
  }
}
