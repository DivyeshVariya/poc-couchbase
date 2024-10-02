package com.poc.couchbase.filters;

import com.poc.couchbase.filters.common.DefaultFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;

@Slf4j
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EmployeeFilter extends DefaultFilter {
  private String employeeId;
  private String mobile;
  private String phoneNumber;
  private String fullName;

  @Override
  public Query toQuery() {
    Query query = super.toQuery();

    addEmployeeIdCriteria(query);
    addFullNameCriteria(query);
    addMobileCriteria(query);
    addPhoneNumberCriteria(query);

    return query;
  }

  private void addFullNameCriteria(Query query) {
    log.trace("Inside addFullNameCriteria Method.");
    if (fullName!=null) {
      query.addCriteria(QueryCriteria
              .where("firstName")
              .regex(fullName));
      query.addCriteria(QueryCriteria
              .where("lastName")
              .regex(fullName));
      log.trace("Fullname criteria added.");
    }
  }

  private void addMobileCriteria(Query query) {
    log.trace("Inside addMobileCriteria Method.");
    if (mobile!=null) {
      query.addCriteria(QueryCriteria
              .where("mobile")
              .is(mobile));
      log.trace("Mobile criteria added.");
    }
  }

  private void addPhoneNumberCriteria(Query query) {
    log.trace("Inside addPhoneNumberCriteria Method.");
    if (phoneNumber!=null) {
      query.addCriteria(QueryCriteria
              .where("phoneNumber")
              .is(phoneNumber));
      log.trace("PhoneNumber criteria added.");
    }
  }

  private void addEmployeeIdCriteria(Query query) {
    log.trace("Inside addEmployeeIdCriteria Method.");
    if (employeeId!=null) {
      query.addCriteria(QueryCriteria
              .where("id")
              .is(employeeId));
      log.trace("EmployeeId criteria added.");
    }
  }

}
