package com.poc.couchbase.filters.common;

import org.springframework.data.couchbase.core.query.Query;

public interface QueryBuilder {
  Query toQuery();
}
