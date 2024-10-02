package com.poc.couchbase.filters.common;

import org.springframework.data.domain.Pageable;

public interface PageBuilder {
  int MAX_PAGE_SIZE = 100;

  Pageable initPage();
}
