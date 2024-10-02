package com.poc.couchbase.filters.common;

import com.couchbase.client.core.deps.com.google.common.base.Strings;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Map;

@Slf4j
@Data
@SuperBuilder
@NoArgsConstructor
public class DefaultFilter implements PageBuilder, QueryBuilder {
  // Constant for default sorting order
  private static final String DEFAULT_SORT_ORDER = "createdOn desc";

  protected Boolean deleted;
  protected Boolean inactive;
  protected int page;
  protected int pageSize;
  protected String sortBy;
  protected Map<String, Object> otherCriteria;
  private Date startDate;
  private Date endDate;

  @Override
  public Pageable initPage() {
    if (this.pageSize <= 0 || this.pageSize > 100) {
      this.pageSize = 100;
    }

    return PageRequest.of(this.page, this.pageSize);
  }

  @Override
  public Query toQuery() {
    Query query = new Query();

    addOtherCriteria(query);
    addDeletedCriteria(query);
    addInactiveCriteria(query);
    addSorting(query);
    addStartDateAndEndDate(query);

    log.trace("Query : {}", query);
    return query;
  }

  private void addStartDateAndEndDate(Query query) {
    if (startDate!=null && endDate!=null) {
      query.addCriteria(QueryCriteria
              .where("createdOn")
              .gte(startDate)
              .lte(endDate));
    }
  }

  private void addOtherCriteria(Query query) {
    if (otherCriteria!=null && !otherCriteria.isEmpty()) {
      otherCriteria.forEach((field, value) -> query.addCriteria(QueryCriteria
              .where(field)
              .is(value)));
    }
  }

  private void addDeletedCriteria(Query query) {
    if (deleted==null) {
      deleted = Boolean.FALSE;
    }
    query.addCriteria(QueryCriteria
            .where("deleted")
            .is(deleted));
  }

  private void addInactiveCriteria(Query query) {
    if (inactive==null) {
      inactive = Boolean.FALSE;
    }
    query.addCriteria(QueryCriteria
            .where("inactive")
            .is(inactive));
  }

  private void addSorting(Query query) {
    if (Strings.isNullOrEmpty(sortBy)) {
      sortBy = DEFAULT_SORT_ORDER;
    }
    query.with(CommonHelper.sortBy(sortBy));
  }
}
